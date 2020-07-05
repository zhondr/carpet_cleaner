FROM nginx:1.17.5-alpine
MAINTAINER Seiit Tursyn <seiit.tursyn@nu.edu.kz>

COPY downloads/arch.zip /

RUN mkdir /app && \
    unzip /arch.zip nginx.conf.template.txt -d /etc/nginx/ && \
    unzip /arch.zip run.sh healthcheck.sh -d /app/ && \
    unzip /arch.zip dockerize -d /usr/local/bin/ && \
    unzip /arch.zip public/* -d /app/ && \
    rm -f /arch.zip && \
    rm -f /etc/nginx/nginx.conf

VOLUME ["/var/log/nginx/"]

HEALTHCHECK CMD ["/app/healthcheck.sh"]

WORKDIR /app
EXPOSE 80
CMD ["/app/run.sh"]
