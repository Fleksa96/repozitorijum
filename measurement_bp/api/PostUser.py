import os
from app import db
from flask_restplus import Resource
from measurement_bp import measurements_api
from measurement_bp.models.User import User
from measurement_bp.schemas.CreateUser import CreateUser
from marshmallow import ValidationError
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


@measurements_api.route("/register/")
class UserAPI(Resource):

    @authentication_required
    def post(self):
        data = request.get_json(force=True)

        validated_data = CreateUser().load(data)
        user = User(username = data["username"], password = data['password'])

        users = db.session.query(User).all()
        if user.username in [u.username for u in users]:
            return {'message': 'Can\'t insert user, already exists.'}, 400

        db.session.add(user)
        db.session.commit()


        return {'message': 'Inserted user.'}, 200

 