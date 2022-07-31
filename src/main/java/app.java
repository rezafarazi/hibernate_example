import Models.users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.*;

public class app
{

    //Main Funtion Start
    public static void main(String []args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Can i help you : ");
        String opration=sc.next();

        if(opration.toUpperCase().equals("INSERT"))
        {
            String name=sc.next();
            String family=sc.next();
            String national_id=sc.next();
            INSERT(name,family,national_id);
        }
        else if(opration.toUpperCase().equals("DELETE"))
        {
            int User_Id=Integer.parseInt(sc.next());
            DELETE(User_Id);
        }
        else if(opration.toUpperCase().equals("SELECT"))
        {
            SELECT();
        }
        else if(opration.toUpperCase().equals("UPDATE"))
        {
            int id=Integer.parseInt(sc.next());
            String name=sc.next();
            String family=sc.next();
            String national_id=sc.next();
            Update(id,name,family,national_id);
        }

    }
    //Main Funtion ENd

    //Insert Data To Database
    public static void INSERT(String name,String family,String national_id)
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
            user.setName(name);
            user.setFamily(family);
            user.setNational_id(national_id);

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

    //Delete Data From Database
    public static void DELETE(int Id)
    {
        try
        {
            StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();
            SessionFactory factory=meta.getSessionFactoryBuilder().build();
            Session session=factory.openSession();
            Transaction t=session.beginTransaction();

            users user=new users();
            user.setId(Id);

            session.delete(user);
            t.commit();
            factory.close();
            session.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //SELECT Table
    public static void SELECT()
    {
        try
        {
            StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();
            SessionFactory factory=meta.getSessionFactoryBuilder().build();
            Session session=factory.openSession();
            Transaction t=session.beginTransaction();

            String hql = "FROM users";
            Query query = session.createQuery(hql);
            List results = query.list();

            for(int i=0;i<results.size();i++)
            {
                users user=(users) results.get(i);
                System.out.println(user.getName());
            }

            t.commit();
            factory.close();
            session.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //Update
    public static void Update(int id,String name,String family,String national_id)
    {
        try
        {
            StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();
            SessionFactory factory=meta.getSessionFactoryBuilder().build();
            Session session=factory.openSession();
            Transaction t=session.beginTransaction();

            users user=new users();
            user.setId(id);
            user.setName(name);
            user.setFamily(family);
            user.setNational_id(national_id);

            session.update(user);
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
