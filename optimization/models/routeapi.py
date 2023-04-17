import requests
try:
    from location import Location # For testing
except:
    from .location import Location # For production


class RouteAPI:
    def __init__(self):
        """
        Initializes an OSRM object with a base URL for the OSRM server.
        :param base_url: A string representing the base URL for the OSRM server.
        """
        self._base_url = "http://router.project-osrm.org"

    def get_trip(self, start: Location, locations, end: Location):
        """
        Sends a GET request to the OSRM server's trip service with a list of locations, start and end points, and returns the server's response.
        :param locations: A list of locations representing the coordinates to include in the trip.
        :param start: A dictionary representing the starting location of the trip.
        :param end: A dictionary representing the ending location of the trip.
        :return: A text containing the OSRM server's response.
        """
        coordinates = ";".join([f"{start.get_long()},{start.get_lat()}"] + [f"{loc.get_long()},{loc.get_lat()}" for loc in locations] + [f"{end.get_long()},{end.get_lat()}"])
        url = self._base_url + "/trip/v1/driving/" + coordinates
        params = {
            "roundtrip": "false",
            "source": "first",
            "destination": "last",
            "steps": "true",
            "geometries": "geojson",
            "overview": "full",
            "annotations": "false",
        }
        response = requests.get(url, params=params)
        return response.text.encode('windows-1254').decode('windows-1254')
