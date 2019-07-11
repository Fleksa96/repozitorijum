import os
from app import db
from flask_restplus import Resource
from measurement_bp import measurements_api
from measurement_bp.models.User import User
from measurement_bp.schemas.CreateUser import CreateUser
from marshmallow import ValidationError

from functools import wraps
from werkzeug.exceptions import Forbidden
from flask import request

def authentication_required(f):
    @wraps(f)
    def wrapped(*args, **kwargs):
        if not request.authorization:
            raise Forbidden
 #   import import pdb; pdb.set_trace()
 
        usrs = db.session.query(User).all()

        print("\n\n\n\n")
        print(usrs)
        print("\n\n\n\n")

        if not request.authorization['username'] in [u.username for u in usrs]:
            print("nepostoji")
            return f(*args, **kwargs)
        else:
            print("postoji")
            raise Forbidden
    return wrapped



@measurements_api.route("/register/")
class UserAPI(Resource):

    @authentication_required
    def post(self):
        data = request.get_json(force=True)

        validated_data = CreateUser().load(data)
        user = User(username = data["username"], password = data['password'])

        db.session.add(user)
        db.session.commit()


        return {'message': 'Inserted user.'}, 200

 