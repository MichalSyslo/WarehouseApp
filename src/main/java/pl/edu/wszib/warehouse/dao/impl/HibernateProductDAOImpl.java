package pl.edu.wszib.warehouse.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.warehouse.dao.IProductDAO;
import pl.edu.wszib.warehouse.model.Product;

import javax.persistence.NoResultException;
import java.util.List;


@Repository
public class HibernateProductDAOImpl implements IProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Product> getAllProducts() {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.warehouse.model.Product");
        List<Product> products = query.getResultList();
        session.close();

        return products;
    }

    @Override
    public Product getProductByID(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery( "FROM pl.edu.wszib.warehouse.model.Product WHERE id = :id");
        query.setParameter("id", id);
        Product product = null;
        try{
            product = query.getSingleResult();
        } catch (NoResultException e){
            System.out.println("Cannot find product");
        }
        session.close();

        return product;
    }

    @Override
    public Product getProductByName(String name) {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery( "FROM pl.edu.wszib.warehouse.model.Product WHERE name = :name");
        query.setParameter("name", name);
        Product product = null;
        try{
            product = query.getSingleResult();
        } catch (NoResultException e){
            System.out.println("Cannot find product");
        }
        session.close();

        return product;
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(product);
            tx.commit();
        } catch (Exception e){
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void persistProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx = session.beginTransaction();
            session.save(product);
            tx.commit();
        } catch (Exception e){
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx=null;
        try{
            tx = session.beginTransaction();
            session.delete(product);
            tx.commit();
        } catch (Exception e){
            if(tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }
}
