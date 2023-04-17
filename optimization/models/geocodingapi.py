import requests
try:
    from location import Location # For testing
except:
    from .location import Location # For production

class GeocodingAPI:
    def __init__(self, base_url='https://geocode.maps.co'):
        """
        Initializes the GeocodingAPI class with the base URL of the API.

        Parameters:
        - base_url: The base URL of the API (default: 'https://geocode.maps.co')
        """
        self.base_url = base_url

    def geocode(self, location: Location):
        """
        Sends a geocoding request to the API and returns the response as a JSON object.

        Parameters:
        - location: A Location object containing the address information to geocode.

        Returns:
        A texxt object containing the geocoding response from the API.
        """
        endpoint = '/search'
        url = f'{self.base_url}{endpoint}'

        PARAMS = {
            'street': f'{location.get_apartment_number()} {location.get_street_name()}',
            'city': f'{location.get_city()}',
            'county': f'{location.get_country()}',
            'town': f'{location.get_town()}',
            'postalcode': f'{location.get_postal_code()}',
            'neighborhood': f'{location.get_neighborhood()}',
        }

        response = requests.get(url, params=PARAMS)
        return response.text.encode('windows-1254').decode('windows-1254')

    def reverse_geocode(self, location: Location):
        """
        Sends a reverse geocoding request to the API and returns the response as a JSON object.

        Parameters:
        - location: A Location object containing the latitude and longitude to reverse geocode.

        Returns:
        A text object containing the reverse geocoding response from the API.
        """
        endpoint = '/reverse'
        url = f'{self.base_url}{endpoint}'

        params = {
            'lat': f'{location.get_lat()}',
            'lon': f'{location.get_long()}'
        }

        response = requests.get(url, params=params)
        return response.text.encode('windows-1254').decode('windows-1254')
