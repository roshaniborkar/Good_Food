import React, { FC, ReactElement } from "react";
import { Card, Col, Typography } from "antd";

import { ProductResponse } from "../../../types/types";
import "./OrderItem.css";

type PropsType = {
    perfume: ProductResponse;
    quantity?: number;
};

const OrderItem: FC<PropsType> = ({ perfume, quantity }): ReactElement => {
    return (
        <Col span={12}>
            <Card
                className={"menu-card"}
                cover={<img className={"menu-card-image"} alt={perfume.productName} src={perfume.imagePath} />}
            >
                <div className={"menu-content"}>
                    <Typography.Text strong>{perfume.productCategory}</Typography.Text>
                    <Typography.Text strong>{perfume.productName}</Typography.Text>
                    <Typography.Text strong>Price: $ {perfume.price}</Typography.Text>
                    <Typography.Text strong>Quantity: {quantity}</Typography.Text>
                </div>
            </Card>
        </Col>
    );
};

export default OrderItem;
