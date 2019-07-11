from marshmallow import Schema, fields, validate


class CreateMeasurement(Schema):
    air_quality = fields.Float(required=True, validate=validate.Range(min=0, max=500))
    temperature = fields.Float(required=True, validate=validate.Range(min=-100, max = 100))
    humidity = fields.Float(required=True, validate=validate.Range(min=0, max = 100))