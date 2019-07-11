import os
from app import db
from flask_restplus import Resource
from measurement_bp import measurements_api
from app import Conf
from measurement_bp.models.Measurement import Measurement
from measurement_bp.schemas.GetSchema import GetSchema

@measurements_api.route("/latest/", methods=['GET'])
class GetMeasurement(Resource):

    
    def get(self):
        data = db.session.query(Measurement).all()


        print("\n\n\n\n")
        print(data)
        print("\n\n\n\n")
            
        data = data[-1]


        get_schema = GetSchema().dump(data)
        return get_schema

