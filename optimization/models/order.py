import route
import customer
import courier

class Order:
    # This method is called when a new Order object is created. It initializes the attributes of the Order.
    def __init__(self, basket, customer: customer.Customer, courier: courier.Courier):
        # - order_id: a unique identifier for the Order (default is None)
        # - basket_id: a unique identifier for the Basket that the Order is created from
        # - customer: the customer who placed the Order
        # - courier: the courier delivering the Order
        # - total_price: the total cost of the Order
        # - markets: a list of Market objects in the Order (default is an empty list)
        # - markets_to_products: a dictionary mapping each Market object to a list of Product objects (default is an empty dictionary)
        # - route: a route object of the delivery route (default is None)
        self.order_id = None
        self.basket_id = basket
        self.customer = customer
        self.courier = courier
        self.total_price = 0
        self.markets = []
        self.markets_to_products = {}
        self.route = None

    def create_route(self):
        # This method sets the route of the Order.
        # will be implemented in the next section
        pass

    def set_customer(self, customer):
        # This method sets the customer of the Order.
        self.customer = customer
    
    def set_courier(self, courier):
        # This method sets the courier ID of the Order.
        self.courier = courier
    
    def set_total_price(self, total_price: float):
        # This method sets the total cost of the Order.
        self.total_price = total_price
    
    def add_product(self, product):
        # This method adds a Product object to the Order and updates the total cost.
        # - product: a Product object to add to the Order
        self.products.append(product)
        self.total_price += product.get_price()
    
    def get_order_id(self):
        # This method returns the Order ID.
        return self.order_id
    
    def get_courier(self):
        # This method returns the courier.
        return self.courier
    
    def get_customer(self):
        # This method returns the customer.
        return self.customer
    
    def get_total_price(self):
        # This method returns the total cost of the Order.
        return self.total_price
    
    def get_products(self):
        # This method returns a list of Product objects in the Order.
        return self.products
    
    def get_route(self):
        # This method returns the route of the Order.
        return self.route
    
    def get_markets(self):
        # This method returns a list of Market objects in the Order.
        return self.markets
    
    def get_markets_to_products(self):
        # This method returns a dictionary mapping each Market object to a list of Product objects.
        return self.markets_to_products
    
    def get_basket(self):
        # This method returns the basket.
        return self.basket
    
