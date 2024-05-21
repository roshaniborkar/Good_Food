import React, { FC, ReactElement } from "react";
import { Carousel } from "antd";
import { Link } from "react-router-dom";
import productLogo from "../../../img/image.png";
import { PRODUCT } from "../../../constants/routeConstants";
import "./CarouselImageSlider.css";

export const sliderItems = [
    {
        id: "85",
        name: "Fresh Fruits",
        url: "https://i.ibb.co/C0vbNcy/dior-ENG.jpg"
    },
    {
        id: "46",
        name: "Fresh Vegetables",
        url: "https://i.ibb.co/C0vbNcy/dior-ENG.jpg"
    },
    {
        id: "46",
        name: "Meat and Seafood",
        url: "https://i.ibb.co/C0vbNcy/dior-ENG.jpg"
    },
    {
        id: "46",
        name: "Cleaning Essentials",
        url: "https://i.ibb.co/C0vbNcy/dior-ENG.jpg"
    },
    {
        id: "46",
        name: "Home and Kitchen",
        url: "https://i.ibb.co/C0vbNcy/dior-ENG.jpg"
    },
    {
        id: "46",
        name: "Beauty and Grooming",
        url: "https://i.ibb.co/C0vbNcy/dior-ENG.jpg"
    }
];

const CarouselImageSlider: FC = (): ReactElement => {
    return (
        <Carousel>
            {sliderItems.map((item) => (
                <div key={item.id} className={"carousel-item-wrapper"}>
                    <Link to={{ pathname: "/menu", state: { id: item.name } }} className={"carousel-link"} />
                    <img src={productLogo} alt={item.name} className={"carousel-img"} />
                </div>
            ))}
        </Carousel>
    );
};

export default CarouselImageSlider;
