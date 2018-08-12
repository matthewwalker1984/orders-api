FROM openjdk:8
ENV PATH=/opt/gradle/gradle-4.9/bin:${PATH}

WORKDIR /opt/gradle
RUN wget -O gradle.zip https://services.gradle.org/distributions/gradle-4.9-bin.zip
RUN unzip gradle.zip
WORKDIR /opt/app

COPY . .

EXPOSE 8003

RUN gradle clean test
