from . import Config

class Production(Config):

    ENV_TYPE = "Production" 

    DB_NAME = "d2ahcqfavaeia4"
    DB_USER = "ntleqhjmibyqmo"
    DB_PASSWORD = "b3b483d90db37045bca3a86b194233c9098100f72d78364692c77f165306a581"
    DB_HOST = "ec2-107-21-120-104.compute-1.amazonaws.com"
    DB_PORT = 5432
    SQLALCHEMY_DATABASE_URI = f'postgresql+psycopg2://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}'


    username = "Aleksa"
    password = "123456"