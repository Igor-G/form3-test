package tech.form3.igorg.interview.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tech.form3.igorg.interview.infrastructure.Form3InfrastructureMarker;
import tech.form3.igorg.interview.infrastructure.listener.SerializationContainerListener;
import tech.form3.igorg.interview.model.Form3ModelMarker;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Infrastructure configuration.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = Form3InfrastructureMarker.class)
@EntityScan(basePackageClasses = {Form3ModelMarker.class, Form3InfrastructureConfig.class})
@EnableJpaRepositories(basePackageClasses = Form3InfrastructureMarker.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Form3InfrastructureConfig {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private final SerializationContainerListener serializationContainerListener;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_LOAD)
                .appendListener(serializationContainerListener);
        registry.getEventListenerGroup(EventType.MERGE)
                .appendListener(serializationContainerListener);
    }

}
