import React, { FC, ReactElement } from "react";
import { Row } from "antd";

import ProductCard from "../../../../components/ProductCard/ProductCard";
import { ProductResponse } from "../../../../types/types";

type PropsType = {
    perfumes: Array<ProductResponse>;
};

const PerfumeCardsSliderItem: FC<PropsType> = ({ perfumes }): ReactElement => {
    return (
        <Row gutter={[16, 16]} style={{ margin: 10, marginTop: 10, marginBottom: 10 }}>
            {perfumes.slice(0, 4).map((perfume) => (
                <ProductCard key={perfume.productId} product={perfume} colSpan={6} />
            ))}
        </Row>
    );
};

export default PerfumeCardsSliderItem;
