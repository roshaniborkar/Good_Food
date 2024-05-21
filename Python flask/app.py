import numpy as np
import base64
from flask import Flask, request, jsonify
from keras.preprocessing import image

from keras.models import load_model
from PIL import Image
from io import BytesIO
from keras.applications.resnet50 import decode_predictions




app = Flask(__name__)

model = load_model('finalmodel.keras')


def preprocess_image(base64_string):
    img_data = base64.b64decode(base64_string)
    img = Image.open(BytesIO(img_data))
    img = img.convert('RGB')
    img = img.resize((224, 224))
    img_array = image.img_to_array(img)    
    return np.expand_dims(img_array, axis=0)


@app.route('/uploadImage', methods=['POST'])
def uploadImage():
    data = request.get_json()
    processed_image = preprocess_image(data)
    decoded_predictions = model.predict(processed_image)
    predictions = model.predict(processed_image)
    decoded_predictions = decode_predictions(predictions, top=100)[0]
    return jsonify(decoded_predictions[0][1]), 200



if __name__ == '__main__':
    app.run(debug=True, port=8090)



