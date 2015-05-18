import com.testehibernate.Bean.Editora;
import com.testehibernate.Dao.EditoraDAO;
import com.testehibernate.Dao.Impl.EditoraDAOImpl;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Map;

/**
 * Created by andrepapazoglu on 15/05/15.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Map metadataMap = session.getSessionFactory().getAllClassMetadata();
            for (Object key : metadataMap.keySet()) {
                final ClassMetadata classMetadata = (ClassMetadata) metadataMap.get(key);
                final String entityName = classMetadata.getEntityName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
            Editora editora = new Editora();
            editora.setNome("teste");
            editora.setEmail("teste@");
            EditoraDAO editoraDAO = new EditoraDAOImpl(session);
            editoraDAO.adicionar(editora);

            List<Editora> editoraList = editoraDAO.listAll();
            System.out.println("editoraList.get(0).getNome() = " + editoraList.get(0).getNome());
            System.out.println("editoraList.get(0).getEmail() = " + editoraList.get(0).getEmail());
        } finally {
            session.close();
        }
    }
}
