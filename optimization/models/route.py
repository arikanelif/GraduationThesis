import location

class Route:
    def __init__(self, start: location.Location, end:location.Location):
        """
        Constructor method to initialize the Route object

        Parameters:
        start (location.Location): The starting location of the route, possibly the couriers's location
        end (location.Location): The ending location of the route, possibly the customer's location
        instructions (dict): The instructions for the route, in the format of a JSON object
        ordered_route (dict, optional): A dictionary that maps each stop (location.Location) to its order of visit (default is an empty dictionary)
        """
        self.start = start
        self.end = end
        self.instructions = None
        self.ordered_route = {}

    def get_start(self):
        """
        Getter method to retrieve the starting location of the route

        Returns:
        location.Location: The starting location of the route
        """
        return self.start

    def set_start(self, start: location.Location):
        """
        Setter method to update the starting location of the route

        Parameters:
        start (location.Location): The new starting location of the route
        """
        self.start = start

    def get_end(self):
        """
        Getter method to retrieve the ending location of the route

        Returns:
        location.Location: The ending location of the route
        """
        return self.end

    def set_end(self, end: location.Location):
        """
        Setter method to update the ending location of the route

        Parameters:
        end (location.Location): The new ending location of the route
        """
        self.end = end

    def get_instructions(self):
        """
        Getter method to retrieve the instructions for the route

        Returns:
        dict: The instructions for the route
        """
        return self.instructions
    
    def set_instructions(self, instructions: dict):
        """
        Setter method to update the instructions for the route

        Parameters:
        instructions (dict): The new instructions for the route
        """
        self.instructions = instructions

    def get_ordered_route(self):
        """
        Getter method to retrieve the order map for each stop

        Returns:
        dict: The order map for each stop
        """
        return self.ordered_route

    def set_ordered_route(self, ordered_route):
        """
        Setter method to update the order map for each stop

        Parameters:
        ordered_route (dict): The new order map for each stop
        """
        self.ordered_route = ordered_route

    def add_stop(self, stop: location.Location):
        """
        Method to add a new stop to the route

        Parameters:
        stop (location.Location): The new stop to add to the route
        """
        num_of_routes = len(list(self.ordered_route.keys()))
        last_location = self.ordered_route[num_of_routes]
        self.ordered_route[num_of_routes] = stop
        self.ordered_route[num_of_routes + 1] = last_location
    
    def get_stops(self):
        """
        Method to retrieve a list of stops in the route

        Returns:
        list[location.Location]: A list of stops in the route
        """
        return list(self.ordered_route.keys())
    
    def create_ordered_route(self, markets: list):
        """
        Method to create an order map for the route

        Parameters:
        markets (list[market.Market]): A list of orders to be delivered
        """
        self.ordered_route[0] = self.start
        
        for i in range(len(markets)):
            self.ordered_route[i + 1] = markets[i].get_location()
        
        self.ordered_route[len(markets) + 1] = self.end
