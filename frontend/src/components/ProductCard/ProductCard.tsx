import React, { FC, ReactElement } from "react";
import { Button, Card, Col, Rate, Typography } from "antd";
import { Link } from "react-router-dom";
import Meta from "antd/lib/card/Meta";
import { DeleteOutlined, EditOutlined, ShoppingCartOutlined } from "@ant-design/icons";

import { ProductResponse as ProductResponse } from "../../types/types";
import { ACCOUNT_ADMIN_PRODUCTS as ACCOUNT_ADMIN_PRODUCTS, PRODUCT } from "../../constants/routeConstants";
import { useCart } from "../../hooks/useCart";
import "./ProductCard.css";

type PropsType = {
    product: ProductResponse;
    colSpan: number;
    edit?: boolean;
    onOpenDelete?: (product: ProductResponse) => void;
};

const ProductCard: FC<PropsType> = ({ product: product, colSpan, edit, onOpenDelete }): ReactElement => {
    const { addToCart } = useCart(product.productId);

    const onClickAddToCart = (event: any) => {
        event.preventDefault();
        addToCart();
    };

    return (
        <Col span={colSpan}>
            <Link to={`${PRODUCT}/${product.productId}`}>
                <Card
                    className={"perfume-card"}
                    cover={<img className={"perfume-card-image"} alt={product.productName} src={product.imagePath} />}
                    hoverable
                    actions={
                        edit
                            ? [
                                  <Link to={`${ACCOUNT_ADMIN_PRODUCTS}/${product.productId}`}>
                                      <Button icon={<EditOutlined />}>Edit</Button>
                                  </Link>,
                                  <Button icon={<DeleteOutlined />} onClick={() => onOpenDelete!(product)} danger>
                                      Delete
                                  </Button>
                              ]
                            : [
                                  <Button icon={<ShoppingCartOutlined />} onClick={onClickAddToCart}>
                                      Add to cart
                                  </Button>
                              ]
                    }
                >
                    <div className={"perfume-card-rate"}>
                        <Rate defaultValue={0 === 0 ? 5 : product.review} disabled />
                        <Typography.Text>{product.reviewsCount} reviews</Typography.Text>
                    </div>
                    <Meta
                        title={product.productName}
                        description={product.productCategory}
                        style={{ textAlign: "center" }}
                    />
                    <Typography.Text className={"perfume-card-price"}>${product.price}.00</Typography.Text>
                </Card>
            </Link>
        </Col>
    );
};

export default ProductCard;
