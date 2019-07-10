import os
from app import db
from flask_restplus import Resource
from measurement_bp import measurement_api
from app import Conf

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



class Measurements(Resource):

    @authentication_required
    @measurement_api.route("/measurement")
    def post(self):
        data = request.get_json(force=True)
        measurement = Measurement(air_quality=data['air_quality'], data["temperature"], data["humidity"])
        db.session.add(measurement)
        db.session.commit()


        return {'message': 'Inserted measurement.'}, 200

 