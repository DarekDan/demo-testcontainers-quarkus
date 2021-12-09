FROM mcr.microsoft.com/mssql/server:2019-CU14-ubuntu-20.04 AS mssql-base

ENV SA_PASSWORD Pass@W0rd
ENV ACCEPT_EULA Y

EXPOSE 1433
USER root
RUN apt update
RUN apt-get -qq install netcat net-tools iputils-ping dnsutils

FROM mssql-base as stackoverflow-2010-db
COPY ./entrypoint.sh /
COPY ./init.sql /
COPY ./StackOverflow2010.mdf /
COPY ./StackOverflow2010_log.ldf /

RUN chmod +x /entrypoint.sh
RUN chmod -x /init.sql
RUN chmod 600 /StackOverflow2010*.*

ENTRYPOINT [ "/bin/bash", "entrypoint.sh" ]
CMD [ "/opt/mssql/bin/sqlservr" ]
