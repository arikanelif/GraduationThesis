import location

class Courier:
    def __init__(self, courier_id, name, location: location.Location, mail, password):
        # This method is called when a new Courier object is created. It initializes the attributes of the Courier.
        # - courier_id: a unique identifier for the Courier
        # - name: the name of the Courier
        # - location: the location of the Courier, in the format of the Location class
        # - mail: the email address of the Courier
        # - password: the password of the Courier's account
        self.courier_id = courier_id
        self.name = name
        self.location = location 
        self.mail = mail
        self.password = password
        self.current_order = None
        self.past_orders = []
    
    def get_courier_id(self):
        # This method returns the Courier ID.
        return self.courier_id
    
    def set_courier_id(self, courier_id):
        # This method sets the Courier ID.
        self.courier_id = courier_id
    
    def get_name(self):
        # This method returns the name of the Courier.
        return self.name
    
    def set_name(self, name):
        # This method sets the name of the Courier.
        self.name = name
    
    def get_location(self):
        # This method returns the location of the Courier.
        return self.location
    
    def set_location(self, location):
        # This method sets the location of the Courier.
        self.location = location
    
    def get_mail(self):
        # This method returns the email address of the Courier.
        return self.mail
    
    def set_mail(self, mail):
        # This method sets the email address of the Courier.
        self.mail = mail
    
    def get_password(self):
        # This method returns the password of the Courier's account.
        return self.password
    
    def set_password(self, password):
        # This method sets the password of the Courier's account.
        self.password = password
    
    def get_current_order(self):
        # This method returns the current order of the Courier.
        return self.current_order
    
    def set_current_order(self, order):
        # This method sets the current order of the Courier.
        self.current_order = order
    
    def complete_order(self):
        # This method adds the current order to the list of past orders.
        if self.current_order:
            self.past_orders.append(self.current_order)
            self.current_order = None
        else:
            print("There is no current order to complete.")
