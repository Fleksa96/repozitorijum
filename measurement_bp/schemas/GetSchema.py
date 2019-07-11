from marshmallow import Schema, fields


class GetSchema(Schema):
    air_quality = fields.Float(required=True)
    temperature = fields.Float(required=True)
    humidity = fields.Float(required=True)
    id = fields.Integer(required=True)
    time_stamp = fields.DateTime(required=True)