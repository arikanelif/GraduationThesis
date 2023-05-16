import psycopg2

class DbConnect:
    def __init__(self, database_name, username, password, hostname, port):
        """
        Initializes a new DbConnect object and establishes a connection to the specified database using the 
        provided parameters.

        :param database_name: name of the database
        :param username: username to connect to the database
        :param password: password to connect to the database
        :param hostname: hostname of the database server
        :param port: port number to connect to the database
        """
        self.database_name = database_name
        self.username = username
        self.password = password
        self.hostname = hostname
        self.port = port
        # create a new connection to the database
        self.conn = psycopg2.connect(
            database=self.database_name,
            user=self.username,
            password=self.password,
            host=self.hostname,
            port=self.port
        )
        # create a cursor object to execute queries
        self.cur = self.conn.cursor()

    def select_all(self, table_name):
        """
        Executes a SELECT * query to retrieve all rows from the specified table.

        :param table_name: name of the table to select data from
        :return: a list of tuples, each tuple containing data for one row
        """
        # execute the SQL query
        self.cur.execute(f"SELECT * FROM {table_name}")
        # fetch all results
        rows = self.cur.fetchall()
        # return the rows as a list of tuples
        return rows

    def select_by_id(self, table_name, id):
        """
        Executes a SELECT query to retrieve data for the row with the specified ID from the specified table.

        :param table_name: name of the table to select data from
        :param id: ID of the row to retrieve
        :return: a tuple containing data for the specified row
        """
        # execute the SQL query
        self.cur.execute(f"SELECT * FROM {table_name} WHERE id = %s", (id,))
        # fetch the result
        row = self.cur.fetchone()
        # return the row as a tuple
        return row

    def insert_data(self, table_name, data):
        """
        Executes an INSERT query to insert a new row into the specified table.

        :param table_name: name of the table to insert data into
        :param data: a list containing data to insert into the table
        """
        # create the SQL query with placeholders for the values to insert
        placeholders = ",".join(["%s"] * len(data))
        columns = ",".join([str(x) for x in data.keys()])
        values = tuple(data.values())
        sql = f"INSERT INTO {table_name} ({columns}) VALUES ({placeholders})"
        # execute the SQL query with the specified data
        self.cur.execute(sql, values)
        # commit the changes to the database
        self.conn.commit()

    def update_data(self, table_name, id, data):
        """
        Executes an UPDATE query to update data for the row with the specified ID in the specified table.

        :param table_name: name of the table to update data in
        :param id: ID of the row to update
        :param data: a dictionary containing the new data to update
        """
        # create the SQL query with placeholders for the new values and the row ID to update
        placeholders = ",".join([f"{k}=%s" for k in data.keys()])
        values = tuple(data.values()) + (id,)
        sql = f"UPDATE {table_name} SET {placeholders} WHERE id = %s"
        # execute the SQL query with the specified data
        self.cur.execute(sql, values)
        # commit the changes to the database
        self.conn.commit()


    def delete_data(self, table_name, id):
        """
        Executes a DELETE query to delete the row with the specified ID from the specified table.

        :param table_name: name of the table to delete data from
        :param id: ID of the row to delete
        """
        # execute the SQL query with the specified row ID
        self.cur.execute(f"DELETE FROM {table_name} WHERE id = %s", (id,))
        # commit the changes to the database
        self.conn.commit()

    def close_connection(self):
        """
        Closes the database connection.
        """
        self.cur.close()
        self.conn.close()
