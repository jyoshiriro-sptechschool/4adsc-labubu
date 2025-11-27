# Estágio Final: Imagem de Runtime Leve
# Usamos uma imagem JRE 21 leve. O eclipse-temurin é uma escolha popular e segura.
FROM eclipse-temurin:21-jre-alpine AS final

# Configurações de Ambiente
# Define o diretório de trabalho dentro do container
WORKDIR /app

# Rótulo (Label) para metadados (opcional, mas boa prática)
LABEL authors="SPTech"
LABEL org.label-schema.schema-version="1.0"
LABEL org.label-schema.name="pets-api"

# Expõe a porta 8080 (informação, não abre a porta no host automaticamente)
EXPOSE 8080

# Copia o arquivo JAR da sua máquina para o diretório de trabalho (/app) dentro do container
# Assuma que 'target/app.jar' é o caminho para seu arquivo JAR local.
COPY backend/api.jar api.jar

# Ponto de entrada (Entrypoint) e Comando de Execução (CMD)
# Executa o JAR. Usar -Djava.security.egd=file:/dev/urandom pode melhorar a inicialização em ambientes Linux.
ENTRYPOINT ["java", "-jar", "api.jar"]

# Você também pode passar variáveis de ambiente do Spring Boot aqui, se precisar
# Exemplo: ENTRYPOINT ["java", "-Dserver.port=8080", "-jar", "app.jar"]