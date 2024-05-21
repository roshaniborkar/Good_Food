import { Col, Row } from "antd";
import React, { FC, ReactElement } from "react";
import { Link } from "react-router-dom";

import { MENU } from "../../../../constants/routeConstants";
import "./SliderBrandsItem.css";

export type BrandType = {
    name: string;
    url: string;
};

type PropsType = {
    brands: Array<BrandType>;
};

const SliderBrandsItem: FC<PropsType> = ({ brands }): ReactElement => {
    return (
        <Row>
            {brands.map((brand: BrandType, index: number) => (
                <Col span={4} key={index} className={"slider-brand-item"}>
                    {/* <Link className={"slider-brand-item-link"} to={{ pathname: MENU, state: { id: brand.name } }} />
                    <img style={{ width: "80%" }} src={brand.url} alt={brand.name} /> */}

                    <button className="_1X-Xv" aria-label={brand.name} data-testid="card-button">
                        <Link to={{ pathname: MENU, state: { id: brand.name } }}>
                            <div className="_1bOXc" style={{ height: "110.895px", width: "98px" }}>
                                <img
                                    src={brand.url}
                                    loading="lazy"
                                    style={{
                                        width: "100%",
                                        height: "100%",
                                        objectFit: "cover",
                                        background: "transparent"
                                    }}
                                />
                            </div>
                            <div
                                aria-label={brand.name}
                                className="sc-aXZVg fQwRYD _3reAM _3RCdI"
                                aria-hidden="true"
                                font-weight="700"
                            >
                                {brand.name}
                            </div>
                        </Link>
                    </button>
                </Col>
            ))}
        </Row>
    );
};

export default SliderBrandsItem;
