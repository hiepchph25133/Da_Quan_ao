import React, { useState, useRef, useMemo, useEffect } from 'react';
import { Table, Space, Button, Input, Form, Modal, notification, Radio, Popconfirm, Tag, Switch } from 'antd';
import {
    PlusOutlined,
    RedoOutlined,
    FormOutlined,
    DeleteOutlined,
    SearchOutlined,
} from '@ant-design/icons';
import './Product.css'
import SupplierService from '~/service/SupplierService';
import FormatDate from '~/utils/format-date';

const { TextArea } = Input;


function Supplier() {

    const [loading, setLoading] = useState(false);

    const [open, setOpen] = useState({ isModal: false, isMode: '', reacord: null });
    const showModal = (mode, record) => {
        setOpen({
            isModal: true,
            isMode: mode,
            record: record,
            reacord: record,
        });
    };

    const hideModal = () => {
        setOpen({ isModal: false });
    };
    const [producers, setProducers] = useState([]);
    // const [deleted, setDeleted] = useState(null);
    const [searchName, setSearchName] = useState(null);
    const [searchPhone, setSearchPhone] = useState(null);
    const [searchEmail, setSearchEmail] = useState(null);

    //  Phân trang

    const [pagination, setPagination] = useState({
        current: 1,
        pageSize: 5,
        total: 0,
    });


    const fetchProducers = async () => {
        try {
            const response = await SupplierService.getAll(
                pagination.current - 1,
                pagination.pageSize,
                searchName,
                searchPhone,
                searchEmail
            );
            setLoading(true);
            console.log('Response:', response);
            console.log('Status:', response.status);
            console.log('Data:', response.data);

            if (response && response.data) {
                const status = response.status || (response.data && response.data.status);

                if (status === 200) {
                    const responseData = response.data;

                    if (Array.isArray(responseData)) {
                        console.log('Response Data:', responseData);
                        const formattedProducers = responseData.map(producer => ({
                            key: producer.id,
                            id: producer.id,
                            code: producer.code,
                            producerName: producer.producerName,
                            email: producer.email,
                            sdt: producer.sdt,
                            dia_chi: producer.dia_chi,
                            ghi_chu: producer.ghi_chu,
                            dateCreate: new Date(producer.dateCreate).toLocaleString(),
                            dateUpdate: producer.dateUpdate ? new Date(producer.dateUpdate).toLocaleString() : 'N/A',
                            status: String(producer.status),  // Chuyển đổi thành chuỗi 
                        }));
                        setProducers(formattedProducers);
                        setPagination({
                            ...pagination,
                            total: response.totalCount,
                        });
                    } else {
                        console.error('Dữ liệu không phải là một mảng.');
                    }
                } else {
                    console.error('Trạng thái không thành công: ', status);
                }
            } else {
                console.error('Không có response hoặc response.data.');
            }
        } catch ({ response, message }) {
            console.error('Lỗi khi gọi API: ', response || message);
        } finally {
            // ...
        }
    };



    useEffect(() => {
        console.log("Fetching producers...");
        fetchProducers();
    }, [pagination.current, pagination.pageSize, searchName, searchPhone, searchEmail]);

    const handleDelete = async (id) => {
        try {
            console.log("Deleting record with ID:", id);
            await SupplierService.delete(id);
            fetchProducers();
        } catch (error) {
            console.error(error);
            notification.error({
                message: 'Thông báo',
                description: 'Đã xảy ra lỗi!',
            });
        }
    };



    // const handleDelete = async (id) => {

    //     await SupplierService.delete(id).then(() => {

    //         fetchProducers();
    //     }).catch(error => {
    //         console.error(error);
    //         notification.error({
    //             message: 'Thông báo',
    //             description: 'Đã xảy ra lỗi!',
    //         });
    //     });

    // };

    const handleReset = () => {

        setSearchEmail(null);
        setSearchName(null);
        setSearchPhone(null);
        // setDeleted(null);

        setPagination({
            ...pagination,
            current: 1,
        });
        // handleTableChange(pagination, null)
    };


    const handleTableChange = (pagination, filters) => {
        console.log(filters)
        // setPagination({
        //     ...pagination,
        // });


        const searchNameFilter = filters?.supplierName;
        if (searchNameFilter) {
            setSearchName(searchNameFilter[0]);
        } else {
            setSearchName(null)
        }

        const searchPhoneFilter = filters?.phoneNumber;
        if (searchPhoneFilter) {
            setSearchPhone(searchPhoneFilter[0]);
        } else {
            setSearchPhone(null)
        }

        const searchEmailFilter = filters?.email;
        if (searchEmailFilter) {
            setSearchEmail(searchEmailFilter[0]);
        } else {
            setSearchEmail(null)
        }

        const statusFilter = filters?.deleted;
        const isNoStatusFilter = !statusFilter || statusFilter.length === 0;

        if (!isNoStatusFilter) {
            const isBothStatus = statusFilter.length === 2;

            // setDeleted(isBothStatus ? null : statusFilter[0]);
        } else {
            // setDeleted(null);
        }
    };

    const getColumnSearchProps = (dataIndex) => ({
        filteredValue: dataIndex === 'name' ? [searchName] : dataIndex === 'phoneNumber' ? [searchPhone] : [searchEmail],
        filterDropdown: ({ setSelectedKeys, selectedKeys, confirm }) => (
            <Input.Search
                placeholder={`Nhập từ khóa...`}
                value={selectedKeys[0]}
                onChange={(e) => setSelectedKeys(e.target.value ? [e.target.value] : [])}
                onSearch={(value) => {
                    setSelectedKeys(value ? [value.trim()] : []);
                    confirm();
                }}
                style={{ display: 'block' }}
            />
        ),
    });


    const columns = [
        {
            title: '#',
            dataIndex: 'id',
            key: 'id',
            width: '5%',
        },
        {
            title: 'Mã',
            dataIndex: 'code',
            key: 'code',
            width: '15%',
        },
        {
            title: 'Tên nhà sản xuất',
            dataIndex: 'producerName',
            key: 'producerName',
            width: '15%',
        },

        {
            title: 'Email',
            dataIndex: 'email',
            key: 'email',
            width: '15%',
        },

        {
            title: 'SĐT',
            dataIndex: 'sdt',
            key: 'sdt',
            width: '15%',
        },

        {
            title: 'Địa chỉ',
            dataIndex: 'dia_chi',
            key: 'dia_chi',
            width: '15%',
        },

        {
            title: 'Ghi Chú',
            dataIndex: 'ghi_chu',
            key: 'ghi_chu',
            width: '15%',
        },

        {
            title: 'Ngày tạo',
            dataIndex: 'dateCreate',
            key: 'dateCreate',
            width: '10%',
        },
        {
            title: 'Ngày sửa',
            dataIndex: 'dateUpdate',
            key: 'dateUpdate',
            width: '14%',
        },
        // {
        //     title: 'Ghi chú',
        //     dataIndex: 'supplierDescribe',
        //     key: 'supplierDescribe',
        //     width: '15%',
        // },
        {
            title: 'Trạng thái',
            key: 'status',
            dataIndex: 'status',
            width: '16%',
            filters: [
                {
                    text: 'Đang hoạt động',
                    value: true,
                },
                {
                    text: 'Ngừng hoạt động',
                    value: false,
                },
            ],
            onFilter: (value, record) => record.deleted === value,
            render: (text) => (
                text ? <Tag style={{ borderRadius: '4px', fontWeight: '450', padding: '0 4px ' }} color="#108ee9">Đang hoạt động</Tag>
                    : <Tag style={{ borderRadius: '4px', fontWeight: '450', padding: '0 4px ' }} color="#f50">Ngừng hoạt động</Tag>
            )
        },
        {
            title: 'Hành động',
            key: 'action',
            width: '10%',
            render: (record) => {

                return <Space size="middle">
                    {/* <Button type="text"
                        icon={<FormOutlined style={{ color: 'rgb(214, 103, 12)' }} />}
                        onClick={() => showModal("edit", record)} /> */}

                    <Button type="text" icon={<FormOutlined style={{ color: 'rgb(214, 103, 12)' }} />} onClick={() => showModal("edit", record)} />

                    <Switch
                        size="small"
                        defaultChecked={record.deleted}
                        onClick={() => record.id && handleDelete(record.id)}
                    />


                </Space>
            }

        },
    ];

    return (
        <>
            <h3 style={{ marginBottom: '16px', float: 'left', color: '#2123bf' }}>Danh sách nhà cung cấp</h3>

            <Button type="primary"
                icon={<PlusOutlined />}
                onClick={() => showModal("add")}
                style={{ marginBottom: '16px', float: 'right', borderRadius: '2px' }} >
                Thêm mới
            </Button>

            <Button type="primary"
                icon={<RedoOutlined style={{ fontSize: '18px' }} />}
                style={{ marginBottom: '16px', float: 'right', marginRight: '6px', borderRadius: '4px', }}
                onClick={handleReset}
            />

            {/* <Table

                // dataSource={producers ? producers.map((producers) => ({ ...producers, key: producers.id, createdAt: FormatDate(producers.createdAt) })) : []}

                // dataSource={producers ? producers.map((producers) => ({
                //     ...producers,
                //     key: producers.id,
                //     createdAt: FormatDate(producers.createdAt)
                // })) : []}


                onChange={handleTableChange}
                // loading={loading}
                columns={columns}
                pagination={{
                    // current: pagination.current,
                    // pageSize: pagination.pageSize,
                    defaultPageSize: 5,
                    pageSizeOptions: ['5', '10', '15'],
                    // total: pagination.total,
                    showSizeChanger: true,
                }}></Table > */}

            {/* <Table
                dataSource={producers}
                columns={columns}
                // loading={loading}
                pagination={{
                    defaultPageSize: 5,
                    pageSizeOptions: ['5', '10', '15'],
                    showSizeChanger: true,
                }}
            /> */}

            <Table
                dataSource={producers}
                columns={columns}
                pagination={{
                    current: pagination.current,
                    pageSize: pagination.pageSize,
                    total: pagination.total,
                    showSizeChanger: true,
                    onChange: (page, pageSize) => setPagination({ ...pagination, current: page, pageSize }),
                    onShowSizeChange: (current, size) => setPagination({ ...pagination, current: 1, pageSize: size }),
                }}
            />

            {/* {open.isModal && <SupplierModal
                isMode={open.isMode}
                reacord={open.reacord || {}}
                hideModal={hideModal}
                isModal={open.isModal}
                producers={producers}
                fetchProducers={fetchProducers} />} */}
            {/* </> */}
            {open.isModal && <ProducerModal
                isMode={open.isMode}
                reacord={open.reacord || {}}
                producers={producers}
                // sizes={sizes}
                hideModal={hideModal}
                isModal={open.isModal}

                fetchProducers={fetchProducers} />}
        </>

    )
};
export default Supplier;


const ProducerModal = ({ isMode, reacord, hideModal, isModal, fetchProducers, producers }) => {


    const [form] = Form.useForm();

    const handleCreate = () => {
        form.validateFields().then(async () => {

            const data = form.getFieldsValue();

            await SupplierService.create(data)
                .then(() => {
                    notification.success({
                        message: 'Thông báo',
                        description: 'Thêm mới thành công!',
                    });
                    fetchProducers();
                    // Đóng modal
                    hideModal();
                })
                .catch(error => {
                    notification.error({
                        message: 'Thông báo',
                        description: 'Thêm mới thất bại!',
                    });
                    console.error(error);
                });

        }).catch(error => {
            console.error(error);
        })

    }


    // const handleUpdate = () => {
    //     console.log('Record ID in handleUpdate:', reacord.id);

    //     form.validateFields().then(async () => {
    //         const data = form.getFieldsValue();
    //         console.log('Record ID in handleUpdate:', reacord.id);

    //         // Kiểm tra nếu ID tồn tại và không phải undefined
    //         if (reacord && reacord.id !== undefined) {
    //             await SupplierService.update(reacord.id, data)
    //                 .then(() => {
    //                     notification.success({
    //                         message: 'Thông báo',
    //                         description: 'Cập nhật thành công!',
    //                     });
    //                     fetchProducers();
    //                     // Đóng modal
    //                     hideModal();
    //                 })
    //                 .catch(error => {
    //                     notification.error({
    //                         message: 'Thông báo',
    //                         description: 'Cập nhật thất bại!',
    //                     });
    //                     console.error(error);
    //                 });
    //         } else {
    //             console.error('ID không hợp lệ:', reacord.id);
    //         }

    //     }).catch(error => {
    //         console.error(error);
    //     })
    // }



    const handleUpdate = () => {
        console.log('Record ID in handleUpdate:', reacord.id);
        form.validateFields().then(async () => {

            const data = form.getFieldsValue();
            console.log('Record ID in handleUpdate:', reacord.id);
            await SupplierService.update(reacord.id, data)
                .then(() => {
                    notification.success({
                        message: 'Thông báo',
                        description: 'Cập nhật thành công!',
                    });
                    fetchProducers();
                    // Đóng modal
                    hideModal();
                })
                .catch(error => {
                    notification.error({
                        message: 'Thông báo',
                        description: 'Cập nhật thất bại!',
                    });
                    console.error(error);
                });

        }).catch(error => {
            console.error(error);
        })

    }

    // const handleUpdate = async () => {
    //     try {
    //         if (reacord && reacord.id !== undefined && !isNaN(reacord.id)) {
    //             console.log('Record ID:', reacord.id);

    //             const formData = await form.validateFields();

    //             // Make sure to include the ID in the formData
    //             formData.id = reacord.id;

    //             await SupplierService.update(reacord.id, formData);

    //             notification.success({
    //                 message: 'Thông báo',
    //                 description: 'Cập nhật thành công!',
    //             });

    //             fetchProducers();
    //             // Đóng modal
    //             hideModal();
    //         } else {
    //             console.error('ID của record không hợp lệ:', reacord);
    //         }
    //     } catch (error) {
    //         notification.error({
    //             message: 'Thông báo',
    //             description: 'Cập nhật thất bại!',
    //         });
    //         console.error(error);
    //     }
    // };




    return (

        <Modal
            title={isMode === "edit" ? "Cập nhật nhà cung cấp" : "Thêm mới một nhà cung cấp"}
            open={isModal}
            onOk={isMode === "edit" ? handleUpdate : handleCreate}
            // onOk={() => handleUpdate(reacord.id)}  // Truyền ID vào hàm handleUpdate
            onCancel={hideModal}
            okText={isMode === "edit" ? "Cập nhật" : "Thêm mới"}
            cancelText="Hủy bỏ"
        >
            <Form
                name="wrap"
                labelCol={{ flex: '100px' }}
                labelAlign="left"
                labelWrap
                wrapperCol={{ flex: 1 }}
                colon={false}
                style={{ maxWidth: 600, marginTop: '25px' }}
                form={form}
                initialValues={{ ...reacord }}
            >


                {/* <Form.Item
                    label="Tên:"
                    name="name"
                    rules={[
                        { required: true, message: 'Vui lòng nhập tên nhà cung cấp!' },
                        {
                            validator: (_, value) => {
                                if (!value) {
                                    return Promise.resolve(); // Không thực hiện validate khi giá trị rỗng
                                }

                                // Thêm kiểm tra xem giá trị có phải là chuỗi không
                                if (typeof value !== 'string') {
                                    return Promise.reject('Giá trị không hợp lệ!');
                                }

                                const trimmedValue = value.trim(); // Loại bỏ dấu cách ở đầu và cuối
                                const lowercaseValue = trimmedValue.toLowerCase(); // Chuyển về chữ thường
                                const isDuplicate = producers.some(
                                    (producers) => producers.name.trim().toLowerCase() === lowercaseValue && producers.id !== reacord.id
                                );

                                if (isDuplicate) {
                                    return Promise.reject('Tên nhà cung cấp đã tồn tại!');
                                }

                                // Kiểm tra dấu cách ở đầu và cuối
                                if (/^\s|\s$/.test(value)) {
                                    return Promise.reject('Tên nhà cung cấp không được chứa dấu cách ở đầu và cuối!');
                                }

                                return Promise.resolve();
                            },
                        },
                    ]}
                >
                    <Input placeholder="Nhập tên..." />
                </Form.Item> */}

                <Form.Item label="Tên:" name="producerName" rules={[{ required: true, message: 'Vui lòng nhập tên kích thước!' }
                    ,
                {
                    validator: (_, value) => {
                        if (!value) {
                            return Promise.resolve(); // Không thực hiện validate khi giá trị rỗng
                        }
                        const trimmedValue = value.trim();
                        const lowercaseValue = trimmedValue.toLowerCase();
                        const isDuplicate = producers.some(
                            (producers) => producers.producerName.trim().toLowerCase() === lowercaseValue && producers.id !== reacord.id
                        );

                        if (isDuplicate) {
                            return Promise.reject('Tên nhà sản xuất đã tồn tại!');
                        }

                        if (/^\s|\s$/.test(value)) {
                            return Promise.reject('Tên nhà sản xuất không được chứa dấu cách ở đầu và cuối!');
                        }

                        return Promise.resolve();
                    },
                }
                    ,
                ]}>
                    <Input placeholder="Nhập tên nhà sản xuất..." />
                </Form.Item>

                <Form.Item
                    label="Email:"
                    name="email"
                    rules={[
                        { type: 'email', message: 'Email không hợp lệ' },
                        { max: 320, message: 'Email không được dài quá 320 ký tự' },
                    ]}
                >
                    <Input placeholder="Nhập email..." />
                </Form.Item>
                <Form.Item
                    label="SĐT:"
                    name="sdt"
                    rules={[
                        { required: true, message: 'Vui lòng nhập số điện thoại!' },
                        ({ getFieldValue }) => ({
                            validator(_, value) {
                                const phoneNumberRegex = /^[0-9]{10,12}$/;

                                if (!value) {
                                    return Promise.resolve();
                                }

                                const trimmedValue = value.trim(); // Loại bỏ dấu cách ở đầu và cuối
                                const lowercaseValue = trimmedValue.toLowerCase(); // Chuyển về chữ thường

                                const isDuplicate = producers.some(
                                    (s) => s.sdt && s.sdt.trim().toLowerCase() === lowercaseValue && s.id !== reacord.id
                                );

                                if (isDuplicate) {
                                    return Promise.reject('Số điện thoại đã tồn tại!');
                                }

                                if (!phoneNumberRegex.test(value)) {
                                    return Promise.reject('Số điện thoại không đúng định dạng!');
                                }

                                return Promise.resolve();
                            },
                        }),
                    ]}
                >
                    <Input placeholder="Nhập số điện thoại..." />
                </Form.Item>




                <Form.Item label="Địa chỉ:" name="dia_chi" rules={[{ required: true, message: 'Vui lòng nhập địa chỉ!' }
                    ,
                {
                    validator: (_, value) => {
                        if (!value) {
                            return Promise.resolve();
                        }
                        const trimmedValue = value.trim(); // Loại bỏ dấu cách ở đầu và cuối
                        const lowercaseValue = trimmedValue.toLowerCase(); // Chuyển về chữ thường
                        // const isDuplicate = Producer.some(
                        //     (supplier) => supplier.address.trim().toLowerCase() === lowercaseValue && supplier.id !== reacord.id
                        // );


                        // if (isDuplicate) {
                        //     return Promise.reject('Tên nhà cung cấp đã tồn tại!');
                        // }
                        // Kiểm tra dấu cách ở đầu và cuối
                        if (/^\s|\s$/.test(value)) {
                            return Promise.reject('Địa chỉ không được chứa dấu cách ở đầu và cuối!');
                        }
                        return Promise.resolve();
                    },
                },
                ]}>
                    <Input placeholder="Nhập địa chỉ..." />
                </Form.Item>

                <Form.Item label="Ghi chú:" name="ghi_chu">
                    <TextArea rows={4} placeholder="Nhập ghi chú..." />
                </Form.Item>

                <Form.Item label="Trạng thái:" name="status" initialValue="DANG_HOAT_DONG">
                    <Radio.Group name="radiogroup" style={{ float: 'left' }}>
                        <Radio value="DANG_HOAT_DONG">Đang hoạt động</Radio>
                        <Radio value="NGUNG_HOAT_DONG">Ngừng hoạt động</Radio>
                    </Radio.Group>
                </Form.Item>


            </Form>
        </Modal>
    );
};

