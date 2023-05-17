class Product:
    def __init__(self, product_id: str, name: str, explanation: str):
        """
        Initializes a Product object with a unique product ID, name, and explanation.

        Args:
            product_id (str): The unique ID of the product.
            name (str): The name of the product.
            explanation (str): The explanation or description of the product.
        """
        self.product_id = product_id
        self.name = name
        self.explanation = explanation
