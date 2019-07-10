from flask import Blueprint, Response
from flask_restplus import Api
from marshmallow import ValidationError
from json import dump
from flask import jsonify 
measurements_bp = Blueprint('measurement', __name__, url_prefix="/measurement")

measurements_api = Api(measurements_bp)


@measurements_api.errorhandler(ValidationError)
def _handle_api_error(ex):
    return "Hell"

from .api.Measurement import Measurements     
