import React, { FC, memo, ReactElement } from "react";
import { Col, Typography } from "antd";

import { ProductResponse } from "../../../types/types";

type PropsType = {
    perfume: ProductResponse;
};

const CartItemInfo: FC<PropsType> = memo(({ perfume }): ReactElement => {
    return (
        <>
            <Col span={8} className={"cart-item-image"}>
                <img src={perfume.imagePath} alt={perfume.productName} style={{ height: 100 }} />
            </Col>
            <Col span={8}>
                <Typography.Title level={3}>{perfume.productCategory}</Typography.Title>
                <Typography.Title level={5}>{perfume.productName}</Typography.Title>
            </Col>
        </>
    );
});

export default CartItemInfo;
