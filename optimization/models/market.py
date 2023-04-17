import location

class Market:
    def __init__(self, market_id, name, location: location.Location):
        """
        Initializes a market with a name, location, and empty products map.

        Args:
            market_id (str): The unique ID of the market.
            name (str): The name of the market.
            location (location.Location): The location of the market in the form of the location class.
            products (dict): The products map of the market.
        """
        self._market_id = market_id
        self._name = name
        self._location = location # location.Location
        self._products = {}  # {product_id: (count, price)}

    @property
    def market_id(self):
        """
        Returns the ID of the market.
        """
        return self._market_id
    
    @market_id.setter
    def market_id(self, value):
        """
        Sets the ID of the market.

        Args:
            value (str): The new ID of the market.
        """
        self._market_id = value

    @property
    def name(self):
        """
        Returns the name of the market.
        """
        return self._name

    @name.setter
    def name(self, value):
        """
        Sets the name of the market.

        Args:
            value (str): The new name of the market.
        """
        self._name = value

    @property
    def location(self):
        """
        Returns the location of the market.
        """
        return self._location

    @location.setter
    def location(self, value: location.Location):
        """
        Sets the location of the market.

        Args:
            value (location): The new location of the market as a location.Location object.
        """
        self._location = value

    @property
    def products(self):
        """
        Returns the products map of the market.
        """
        return self._products

    def add_product(self, product_id, count, price = 0.0):
        """
        Adds the specified number of products with the given ID to the market.

        Args:
            product_id (str): The ID of the product to add.
            count (int): The number of products to add.
            price (float): The price of the product.
        """
        if product_id in self.products:
            self.products[product_id][0] += count
        else:
            self.products[product_id] = (count, price)

    def remove_product(self, product_id, count):
        """
        Removes the specified number of products with the given ID from the market.

        Args:
            product_id (str): The ID of the product to remove.
            count (int): The number of products to remove.
        """
        if product_id in self.products:
            if self.products[product_id][0] > count:
                self.products[product_id][0] -= count
            else:
                del self.products[product_id]
        else:
            raise ValueError("Product not found in market")

    def update_product_count(self, product_id, new_count):
        """
        Updates the count of the specified product in the market.

        Args:
            product_id (str): The ID of the product to update.
            new_count (int): The new count for the product.
        """
        self.products[product_id][0] = new_count
