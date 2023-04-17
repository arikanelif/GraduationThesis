import location

class Customer:
    def __init__(self, name, location: location.Location, email, password, id):
        """
        Initialize a new instance of the Customer class.
        Args:
            name (str): The name of the customer.
            location (location.Location): The location of the customer.
            email (str): The email address of the customer.
            password (str): The password of the customer's account.
            id (int): The unique ID of the customer.
        """
        self.name = name
        self.location = location
        self.email = email
        self.password = password
        self.id = id
        self.orders = [] # A list to keep track of the customer's orders

    def get_name(self):
        """Get the name of the customer."""
        return self.name

    def set_name(self, name):
        """Set the name of the customer."""
        self.name = name

    def get_location(self):
        """Get the location of the customer."""
        return self.location

    def set_location(self, location):
        """Set the location of the customer."""
        self.location = location

    def get_email(self):
        """Get the email address of the customer."""
        return self.email

    def set_email(self, email):
        """Set the email address of the customer."""
        self.email = email

    def get_password(self):
        """Get the password of the customer's account."""
        return self.password

    def set_password(self, password):
        """Set the password of the customer's account."""
        self.password = password

    def get_id(self):
        """Get the unique ID of the customer."""
        return self.id

    def set_id(self, id):
        """Set the unique ID of the customer."""
        self.id = id

    def get_orders(self):
        """Get the list of orders placed by the customer."""
        return self.orders

    def add_order(self, order):
        """
        Add a new order to the list of orders placed by the customer.
        Args:
            order (Order): An instance of the Order class representing the new order.
        """
        self.orders.append(order)

    def remove_order(self, order):
        """
        Remove an order from the list of orders placed by the customer.
        Args:
            order (Order): An instance of the Order class representing the order to be removed.
        """
        if order in self.orders:
            self.orders.remove(order)

    def view_past_orders(self):
        """Print a list of the customer's past orders."""
        print("Past orders:")
        for order in self.orders:
            if order.is_delivered():
                print(f"- Order #{order.get_id()}: Delivered")
            else:
                print(f"- Order #{order.get_id()}: Not delivered yet")
