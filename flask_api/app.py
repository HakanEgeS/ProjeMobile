from flask import Flask, jsonify
import mysql.connector

app = Flask(__name__)

# --- SHUTTLE ---
@app.route('/shuttle', methods=['GET'])
def get_shuttle():
    try:
        conn = mysql.connector.connect(
            host="localhost",
            user="root",
            password="",  # Şifre varsa ekle
            database="campus"
        )
        cursor = conn.cursor(dictionary=True)
        cursor.execute("SELECT * FROM shuttle")
        result = cursor.fetchall()
        cursor.close()
        conn.close()
        return jsonify(result)
    except mysql.connector.Error as err:
        return jsonify({"error": str(err)}), 500

# --- CARPOOL ---
@app.route('/carpool', methods=['GET'])
def get_carpool():
    try:
        conn = mysql.connector.connect(
            host="localhost",
            user="root",
            password="",
            database="campus"
        )
        cursor = conn.cursor(dictionary=True)
        cursor.execute("SELECT * FROM carpool")
        result = cursor.fetchall()
        cursor.close()
        conn.close()
        return jsonify(result)
    except mysql.connector.Error as err:
        return jsonify({"error": str(err)}), 500

# --- ANNOUNCEMENTS ---
@app.route('/announcements', methods=['GET'])
def get_announcements():
    try:
        conn = mysql.connector.connect(
            host="localhost",
            user="root",
            password="",
            database="campus"
        )
        cursor = conn.cursor(dictionary=True)
        cursor.execute("SELECT * FROM announcements")
        result = cursor.fetchall()
        cursor.close()
        conn.close()
        return jsonify(result)
    except mysql.connector.Error as err:
        return jsonify({"error": str(err)}), 500

# --- SUNUCU BAŞLANGICI ---
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
