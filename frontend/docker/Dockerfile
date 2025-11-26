# 1. Define a imagem base do NGINX
FROM nginx:latest

# 2. Copia seu arquivo de configuração customizado para o diretório
# de configuração padrão do NGINX dentro do contêiner.
# Ao copiar para /etc/nginx/conf.d/default.conf, você sobrescreve
# a configuração padrão que vem com a imagem NGINX.
COPY docker/default.conf /etc/nginx/conf.d/default.conf

# 3. Arquivos HTML estáticos customizados,
COPY dist/ /usr/share/nginx/html/

# 3. Define a porta que o contêiner irá expor.
EXPOSE 80

# 4. O comando padrão do NGINX (mantido da imagem base do ngnix)
CMD ["nginx", "-g", "daemon off;"]