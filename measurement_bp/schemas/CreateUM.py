from marshmallow import Schema, fields, validate


class CreateUM(Schema):
    username = fields.String(required=True)
    password = fields.String(required=True)
    air_quality = fields.Float(required=True, validate=validate.Range(min=0, max=500))
    temperature = fields.Float(required=True, validate=validate.Range(min=-100, max = 100))
    humidity = fields.Float(required=True, validate=validate.Range(min=0, max = 100))

