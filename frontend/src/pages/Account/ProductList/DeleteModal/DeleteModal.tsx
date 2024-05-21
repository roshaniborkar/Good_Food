import React, { FC, ReactElement } from "react";
import { Col, Modal, Row, Typography } from "antd";

import { ProductResponse } from "../../../../types/types";
import "./DeleteModal.css";

type PropsType = {
    visible: boolean;
    deletePerfumeHandler: () => void;
    handleCancel: () => void;
    perfumeInfo?: ProductResponse;
};

const DeleteModal: FC<PropsType> = ({ visible, deletePerfumeHandler, handleCancel, perfumeInfo }): ReactElement => {
    return (
        <Modal title="Delete perfume" open={visible} onOk={deletePerfumeHandler} onCancel={handleCancel}>
            <Row>
                <Col span={12} className={"delete-modal-perfume-image-wrapper"}>
                    <img
                        className={"delete-modal-perfume-image"}
                        alt={perfumeInfo?.productName}
                        src={perfumeInfo?.imagePath}
                    />
                </Col>
                <Col span={12}>
                    <Typography.Text>Are you sure too delete?</Typography.Text>
                    <Typography.Title level={5}>{perfumeInfo?.productCategory}</Typography.Title>
                    <Typography.Title level={5}>{perfumeInfo?.productName}</Typography.Title>
                </Col>
            </Row>
        </Modal>
    );
};

export default DeleteModal;
