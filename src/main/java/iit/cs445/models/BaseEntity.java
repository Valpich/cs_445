package iit.cs445.models;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configurable
@MappedSuperclass
public abstract class BaseEntity<ID, Type> {

    @Autowired
    @Transient
    protected LocalSessionFactoryBean localSessionFactoryBean;

    @Column(name = "creation_time", nullable = false)
    private Date creationTime;

    @Column(name = "modification_time", nullable = false)
    private Date modificationTime;

    @Version
    private long version;

    public BaseEntity() {
        Date now = new Date();
        this.creationTime = now;
        this.modificationTime = now;
    }

    public abstract ID getId();

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "creationTime=" + creationTime +
                ", modificationTime=" + modificationTime +
                ", version=" + version +
                '}';
    }

    public void saveNew() {
        Session session = localSessionFactoryBean.getObject().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(this);
            tx.commit();
        } catch (Exception exc) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, exc.toString());
        } finally {
            session.close();
        }
    }

    public void update() {
        Session session = localSessionFactoryBean.getObject().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.update(this);
            tx.commit();
        }catch(OptimisticLockException ole){
            Logger.getLogger(getClass().getName()).log(Level.WARNING, "OLE caught "+ole.toString());
            session.close();
            try {
                session = localSessionFactoryBean.getObject().openSession();
                Transaction tx = session.beginTransaction();
                session.merge(this);
                tx.commit();
            }catch (Exception exc) {
                    Logger.getLogger(getClass().getName()).log(Level.WARNING, exc.toString());
                } finally {
                    session.close();
                }
        }catch (Exception exc) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, exc.toString());
        } finally {
            session.close();
        }
    }

    public void delete() {
        Session session = localSessionFactoryBean.getObject().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.delete(this);
            tx.commit();
        } catch (Exception exc) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, exc.toString());
        } finally {
            session.close();
        }
    }

    public List<Type> listAll() {
        Session session = localSessionFactoryBean.getObject().openSession();
        List<Type> results = null;
        try {
            String query = "select className from " + getClass().getSimpleName() + " className";
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Doing list all query: " + query);
            results = session.createQuery(query).list();
            if (results != null) {
                for (Type t : results)
                    Logger.getLogger(getClass().getName()).log(Level.INFO, t.toString());
            }
        } catch (Exception exc) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, exc.toString());
        } finally {
            session.close();
        }
        return results;
    }

    public Type findById(Long id) {
        Session session = localSessionFactoryBean.getObject().openSession();
        List<Type> results = null;
        try {
            String query = "select className from " + getClass().getSimpleName() + " className where className.id = :id";
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Doing find by id query: " + query);
            results = session.createQuery(query).setParameter("id", id).list();
            if (results != null) {
                for (Type t : results) {
                    Logger.getLogger(getClass().getName()).log(Level.INFO, t.toString());
                    return t;
                }
            }
            Logger.getLogger(getClass().getName()).log(Level.INFO, "No user found.");
        } catch (Exception exc) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, exc.toString());
        } finally {
            session.close();
        }
        return null;
    }
}

