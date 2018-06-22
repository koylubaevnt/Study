//Создание ConnectionFactory  для JMS ресурса 
asadmin --user admin --host localhost --port 4848 create-jms-resource --restype javax.jms.ConnectionFactory jms/ConnectionFactory

// Создание пунктов назначения
asadmin --user admin --host localhost --port 4848 create-jms-resource --restype javax.jms.Queue --enabled=true --property Name=PhysicalQueue jms/Queue
asadmin --user admin --host localhost --port 4848 create-jms-resource --restype javax.jms.Topic --enabled=true --property Name=PhysicalTopic jms/Topic