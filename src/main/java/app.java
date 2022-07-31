import Models.users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class app
{

    public static void main(String []args)
    {

        try
        {
            StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();
            SessionFactory factory=meta.getSessionFactoryBuilder().build();
            Session session=factory.openSession();
            Transaction t=session.beginTransaction();

            users user=new users();
            //user.setId(1);
            user.setName("Reza");
            user.setFamily("Fta");
            user.setNational_id("85");

            session.save(user);
            t.commit();
            factory.close();
            session.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

}
