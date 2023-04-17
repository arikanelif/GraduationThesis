class Location:
    """
    Represents a location with its latitude, longitude, and address information.
    """
    def __init__(self, lat: float = None, long: float = None, street_name: str = None, street_name_2: str = None,
                 neighborhood: str = None, town: str = None, city: str = None, country: str = None, state: str = None,
                 apartment_number: int = None, inner_door_number: int = None, postal_code: int = None,
                 address_explanation: str = None):
        """
        Creates a new Location object with the given latitude, longitude, and address information.

        Args:
            lat (float): The latitude of the location.
            long (float): The longitude of the location.
            street_name (str, optional): The name of the street. Defaults to None.
            street_name_2 (str, optional): The name of a second street if applicable. Defaults to None.
            neighborhood (str, optional): The name of the neighborhood. Defaults to None.
            town (str, optional): The name of the town. Defaults to None.
            city (str, optional): The name of the city. Defaults to None.
            country (str, optional): The name of the country. Defaults to None.
            state (str, optional): The name of the state or province. Defaults to None.
            apartment_number (int, optional): The number of the apartment. Defaults to None.
            inner_door_number (int, optional): The number of the inner door. Defaults to None.
            postal_code (int, optional): The postal code of the location. Defaults to None.
            address_explanation (str, optional): An explanation of the address. Defaults to None.
        """
        self._lat = lat
        self._long = long
        self._street_name = street_name
        self._street_name_2 = street_name_2
        self._neighborhood = neighborhood
        self._town = town
        self._city = city
        self._country = country
        self._state = state
        self._apartment_number = apartment_number
        self._inner_door_number = inner_door_number
        self._postal_code = postal_code
        self._address_explanation = address_explanation

    def __str__(self):
        """
        Returns a string representation of the location.

        Returns:
            str: The string representation of the location.
        """
        address = self._address_explanation if self._address_explanation else ""
        return f"({self._lat}, {self._long}) {address}"

    def get_lat(self) -> float:
        """
        Returns the latitude of the location.

        Returns:
            float: The latitude of the location.
        """
        return self._lat

    def set_lat(self, lat: float):
        """
        Sets the latitude of the location.

        Args:
            lat (float): The new latitude of the location.
        """
        self._lat = lat

    def get_long(self) -> float:
        """
        Returns the longitude of the location.

        Returns:
            float: The longitude of the location.
        """
        return self._long

    def set_long(self, long: float):
        """
        Sets the longitude of the location.

        Args:
            long (float): The new longitude of the location.
        """
        self._long = long

    def get_street_name(self) -> str:
        """
        Returns the name of the street.

        Returns:
            str: The name of the street.
        """
        return self._street_name

    def set_street_name(self, street_name: str):
        """
        Sets the name of the street.

        Args:
            street_name (str): The new name of the street.
        """
        self._street_name = street_name
    
    def get_street_name_2(self) -> str:
        """
        Returns the name of the second street.

        Returns:
            str: The name of the second street.
        """
        return self._street_name_2
    
    def set_street_name_2(self, street_name_2: str):
        """
        Sets the name of the second street.

        Args:
            street_name_2 (str): The new name of the second street.
        """
        self._street_name_2 = street_name_2

    def get_neighborhood(self) -> str:
        """
        Returns the name of the neighborhood.

        Returns:
            str: The name of the neighborhood.
        """
        return self._neighborhood
    
    def set_neighborhood(self, neighborhood: str):
        """
        Sets the name of the neighborhood.

        Args:
            neighborhood (str): The new name of the neighborhood.
        """
        self._neighborhood = neighborhood
    
    def get_town(self) -> str:
        """
        Returns the name of the town.

        Returns:
            str: The name of the town.
        """
        return self._town
    
    def set_town(self, town: str):
        """
        Sets the name of the town.

        Args:
            town (str): The new name of the town.
        """
        self._town = town

    def get_city(self) -> str:
        """
        Returns the name of the city.

        Returns:
            str: The name of the city.
        """
        return self._city
    
    def set_city(self, city: str):
        """
        Sets the name of the city.

        Args:
            city (str): The new name of the city.
        """
        self._city = city

    def get_country(self) -> str:
        """
        Returns the name of the country.

        Returns:
            str: The name of the country.
        """
        return self._country
    
    def set_country(self, country: str):
        """
        Sets the name of the country.

        Args:
            country (str): The new name of the country.
        """
        self._country = country
    
    def get_state(self) -> str:
        """
        Returns the name of the state or province.

        Returns:
            str: The name of the state or province.
        """
        return self._state
    
    def set_state(self, state: str):
        """
        Sets the name of the state or province.

        Args:
            state (str): The new name of the state or province.
        """
        self._state = state
    
    def get_apartment_number(self) -> int:
        """
        Returns the number of the apartment.

        Returns:
            str: The number of the apartment.
        """
        return self._apartment_number
    
    def set_apartment_number(self, apartment_number: int):
        """
        Sets the number of the apartment.

        Args:
            apartment_number (str): The new number of the apartment.
        """
        self._apartment_number = apartment_number

    def get_inner_door_number(self) -> int:
        """
        Returns the number of the inner door.

        Returns:
            str: The number of the inner door.
        """
        return self._inner_door_number
    
    def set_inner_door_number(self, inner_door_number: int):
        """
        Sets the number of the inner door.

        Args:
            inner_door_number (str): The new number of the inner door.
        """
        self._inner_door_number = inner_door_number
    
    def get_postal_code(self) -> int:
        """
        Returns the postal code of the location.

        Returns:
            str: The postal code of the location.
        """
        return self._postal_code
    
    def set_postal_code(self, postal_code: int):
        """
        Sets the postal code of the location.

        Args:
            postal_code (str): The new postal code of the location.
        """
        self._postal_code = postal_code

    def get_address_explanation(self) -> str:
        """
        Returns an explanation of the address.

        Returns:
            str: An explanation of the address.
        """
        return self._address_explanation
    
    def set_address_explanation(self, address_explanation: str):
        """
        Sets an explanation of the address.

        Args:
            address_explanation (str): The new explanation of the address.
        """
        self._address_explanation = address_explanation

    def to_dict(self) -> dict:
        """
        Returns a dictionary representation of the location.

        Returns:
            dict: The dictionary representation of the location.
        """
        return {
            "latitude": self._lat,
            "longitude": self._long,
            "street_name": self._street_name,
            "street_name_2": self._street_name_2,
            "neighborhood": self._neighborhood,
            "city": self._city,
            "country": self._country,
            "state": self._state,
            "apartment_number": self._apartment_number,
            "inner_door_number": self._inner_door_number,
            "postal_code": self._postal_code,
            "address_explanation": self._address_explanation
        }

