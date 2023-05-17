from flask import Flask, jsonify
from flask_restful import Resource, Api
from flask_cors import CORS
import psycopg2
from models.dbConnect import DbConnect

app = Flask(__name__)
api = Api(app)
CORS(app)

class Locations (Resource):
    def get(self):
        db = DbConnect("qddaimwj", "qddaimwj", "aKYmIOp789I16fnvoSEY79iY9Bbc0qDt", "horton.db.elephantsql.com", "5432")
        ans = db.select_all("location")
        return ans

api.add_resource(Locations, '/locations')

class Couriers (Resource):
    def get(self):
        db = DbConnect("qddaimwj", "qddaimwj", "aKYmIOp789I16fnvoSEY79iY9Bbc0qDt", "horton.db.elephantsql.com", "5432")
        ans = db.select_all("courier")
        return ans

api.add_resource(Couriers, '/couriers')

class Customers (Resource):
    def get(self):
        db = DbConnect("qddaimwj", "qddaimwj", "aKYmIOp789I16fnvoSEY79iY9Bbc0qDt", "horton.db.elephantsql.com", "5432")
        ans = db.select_all("customer")
        return ans
    
api.add_resource(Customers, '/customers')

class Home (Resource):
    def get(self):
        return "Hello, World!"

api.add_resource(Home, '/')

class PickCourier(Resource):
    def get(self, customer_id):
        db = DbConnect("qddaimwj", "qddaimwj", "aKYmIOp789I16fnvoSEY79iY9Bbc0qDt", "horton.db.elephantsql.com", "5432")
        ans = db.pickCourierAtBeginning(customer_id)
        return ans

api.add_resource(PickCourier, '/pickCourier/<customer_id>')

if __name__ == '__main__':
    app.run()