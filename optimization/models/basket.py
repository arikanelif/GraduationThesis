class Basket:
    """
    A class representing a basket of products purchased from multiple markets.
    
    Attributes:
    -----------
    map : dict
        A dictionary mapping each market to a dictionary of products and their counts.
    total_cost : float
        The total cost of all the products in the basket.
    """
    
    def __init__(self, map={}):
        """
        Initializes a new instance of the Basket class.
        
        Parameters:
        -----------
        map : dict
            A dictionary mapping each market to a dictionary of products and their counts.
        """
        self.map = map
        self.total_cost = self.calculate_total_cost()
        
    def calculate_total_cost(self):
        """
        Calculates the total cost of all the products in the basket.
        """
        total_cost = 0
        for market in self.map.values():
            for product, count in market.items():
                total_cost += product.price * count
        return total_cost
    
    def add_product(self, market, product, count=1):
        """
        Adds a product to the basket from a specific market.
        
        Parameters:
        -----------
        market : Market
            The market from which the product is purchased.
        product : Product
            The product to add to the basket.
        count : int
            The number of the product to add to the basket.
        """
        if market in self.map:
            if product in self.map[market]:
                self.map[market][product] += count
            else:
                self.map[market][product] = count
        else:
            self.map[market] = {product: count}
        self.total_cost = self.calculate_total_cost()
        
    def remove_product(self, market, product, count=1):
        """
        Removes a product from the basket from a specific market.
        
        Parameters:
        -----------
        market : Market
            The market from which the product was purchased.
        product : Product
            The product to remove from the basket.
        count : int
            The number of the product to remove from the basket.
        """
        if market in self.map and product in self.map[market]:
            self.map[market][product] -= count
            if self.map[market][product] <= 0:
                del self.map[market][product]
                if not self.map[market]:
                    del self.map[market]
            self.total_cost = self.calculate_total_cost()
        
    def get_market_products(self, market):
        """
        Returns a dictionary of all the products and their counts purchased from a specific market.
        
        Parameters:
        -----------
        market : Market
            The market from which the products were purchased.
            
        Returns:
        --------
        dict
            A dictionary of all the products and their counts purchased from the market.
        """
        return self.map.get(market, {})
    
    def get_total_cost(self):
        """
        Returns the total cost of all the products in the basket.
        
        Returns:
        --------
        float
            The total cost of all the products in the basket.
        """
        return self.total_cost
    
    def get_product_count(self, product):
        """
        Returns the count of a specific product in the basket.
        
        Parameters:
        -----------
        product : Product
            The product for which to return the count.
            
        Returns:
        --------
        int
            The count of the product in the basket.
        """
        product_count = 0
        for market in self.map.values():
            if product in market:
                product_count += market[product]
        return product_count
