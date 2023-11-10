# Utilisation de l'image WildFly en tant que serveur d'application
FROM quay.io/wildfly/wildfly:latest-jdk20

# Supprimer l'application par défaut de WildFly
RUN rm -rf /opt/jboss/wildfly/standalone/deployments/*

# Copier votre fichier WAR dans le dossier deployments de WildFly
COPY target/AMIRE.war /opt/jboss/wildfly/standalone/deployments/

# Port sur lequel WildFly sera en écoute
EXPOSE 8080

# Commande pour démarrer WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]

# Commande pour construire l'image Docker:
# docker build -t prograv/amire:latest .