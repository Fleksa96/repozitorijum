import os
from app import db
from flask_restplus import Resource
from measurement_bp import measurements_api
from app import Conf
from measurement_bp.models.Measurement import Measurement

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



@measurements_api.route("/")
class Measurements(Resource):

    @authentication_required
    def post(self):
        data = request.get_json(force=True)
        measurement = Measurement(air_quality=data['air_quality'], temperature = data["temperature"], humidity = data["humidity"])
        db.session.add(measurement)
        db.session.commit()


        return {'message': 'Inserted measurement.'}, 200

 