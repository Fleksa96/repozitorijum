from marshmallow import Schema, fields, validate


class CreateUser(Schema):
    username = fields.String(required=True)
    password = fields.String(required=True)