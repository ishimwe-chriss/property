package auca.ac.rw.DAO;

import auca.ac.rw.HibernateUtil;
import auca.ac.rw.model.Property;
import org.hibernate.*;
import java.util.List;

public class PropertyDao {
    public void saveProperty(Property property) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.persist(property);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateProperty(Property property) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            // Retrieve the existing property
            Property existingProperty = session.get(Property.class, property.getPropertyID());
            if (existingProperty != null) {
                // Update the existing property's fields
                existingProperty.setOwnerID(property.getOwnerID());
                existingProperty.setAddress(property.getAddress());
                existingProperty.setSize(property.getSize());
                existingProperty.setValuation(property.getValuation());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Retrieve a Property by ID
    public Property getProperty(String propertyID) {
        Property property = null;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            property = session.get(Property.class, propertyID);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return property;
    }

    // List All Properties
    public List<Property> getAllProperties() {
        List<Property> properties = null;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            properties = session.createQuery("from Property", Property.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    // Delete Property by ID
    public void deleteProperty(String propertyID) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Property property = session.get(Property.class, propertyID);
            if (property != null) {
                session.delete(property);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
