from marshmallow import Schema, fields


class CreateMeasurement(Schema):
    air_quality = fields.Float(required=True)
    temperature = fields.Float(required=True)
    humidity = fields.Float(required=True)