import os
from app import db
from flask_restplus import Resource
from measurement_bp import measurements_api
from app import Conf
from measurement_bp.models.Measurement import Measurement
from measurement_bp.schemas.CreateMeasurementSchema import CreateMeasurement
from marshmallow import ValidationError
import pandas as pd

from functools import wraps
from werkzeug.exceptions import Forbidden
from flask import request

def authentication_required(f):
    @wraps(f)
    def wrapped(*args, **kwargs):
        if not request.authorization:
            raise Forbidden

        if request.authorization['username'] ==Conf.username and request.authorization['password']==Conf.password:
            return f(*args, **kwargs)
        else:
            raise Forbidden
    return wrapped


def export():
    measurements = db.session.query(Measurement).all()
    ret = []
    for measure in measurements:
        ret.append({ "air_quality" : measure.air_quality, "humidity" : measure.humidity, "temperature" : measure.temperature })
     
    df = pd.DataFrame(ret)
    df.to_csv('out.csv', index=False) 

@measurements_api.route("/")
class Measurements(Resource):

    @authentication_required
    def post(self):
        data = request.get_json(force=True)

        validated_data = CreateMeasurement().load(data)
        measurement = Measurement(air_quality=data['air_quality'], temperature = data["temperature"], humidity = data["humidity"])
        db.session.add(measurement)
        db.session.commit()

        export()

        return {'message': 'Inserted measurement.'}, 200

 