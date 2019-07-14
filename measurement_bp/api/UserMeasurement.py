import os
from app import db
from flask_restplus import Resource

from app import Conf
from measurement_bp.models.Measurement import Measurement
from measurement_bp.models.User import User
from measurement_bp.schemas.CreateUM import CreateUM

from measurement_bp import measurements_api


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



@measurements_api.route("/user_input/")
class UserMeasurement(Resource):

    @authentication_required
    def post(self):
        data = request.get_json(force=True)

        validated_data = CreateUM().load(data)

        user = User(username=data['username'], password=data['password'])
        users = db.session.query(User).all()

        #ovde sad treba provera da li u bazu postavlja user koji postoji
        if user.username not in [u.username for u in users]:
            return {'message': 'User does not exist in database'}, 400
        #ako user ne postoji onda greska


        measurement = Measurement(air_quality=data['air_quality'], temperature = data["temperature"], humidity = data["humidity"])
        
        db.session.add(measurement)
        db.session.commit()

        return {'message': 'Inserted measurement by  {}'.format(user.username)}, 200

 