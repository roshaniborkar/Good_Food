import React, { FC, ReactElement } from "react";
import { Button, Card, Col, Form, FormInstance, Input, Rate, Row, Typography, message } from "antd";
import { SendOutlined } from "@ant-design/icons";

import { ReviewResponse, ReviewError } from "../../../types/types";
import ReviewItem from "./ReviewItem/ReviewItem";
import { ReviewData } from "../Product";
import "./ProductReviews.css";

type PropType = {
    reviews: ReviewResponse[];
    reviewErrors: Partial<ReviewError>;
    addReview: (data: ReviewData) => void;
    form?: FormInstance<any>;
};

const ProductReviews: FC<PropType> = ({ reviews, reviewErrors, addReview, form }): ReactElement => {
    const { authorError, messageError, ratingError } = reviewErrors;
    const review1 = { id: 1, author: "Alice", message: "Nice product!", rating: 5, date: "01-05-2024" };
    const review2 = { id: 1, author: "James", message: "Better than expected : )ß", rating: 4, date: "11-02-2024" };
    const review3 = { id: 1, author: "Sam", message: "Didnt like it :(", rating: 2, date: "20-03-2024" };
    return (
        <>
            <Row>
                <Col span={24} className={"product-reviews-title"}>
                    <Typography.Title level={3}>Reviews</Typography.Title>
                </Col>
            </Row>
            <Row>
                {reviews.length === 0 ? (
                    <Col span={24} className={"product-reviews-title"}>
                        <ReviewItem key={1} review={new ReviewResponse(review1)} />
                        <ReviewItem key={1} review={new ReviewResponse(review2)} />
                        <ReviewItem key={1} review={new ReviewResponse(review3)} />
                    </Col>
                ) : (
                    <Col span={24}>
                        {reviews.map((review) => (
                            <ReviewItem key={review.id} review={review} />
                        ))}
                    </Col>
                )}
            </Row>
        </>
    );
};

export default ProductReviews;
