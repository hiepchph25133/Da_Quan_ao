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
import ColorService from '~/service/ColorService';
import FormatDate from '~/utils/format-date';

const { TextArea } = Input;

function Color() {

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

    const [colors, setColors] = useState([]);

    // const [pagination, setPagination] = useState({ current: 1, pageSize: 5, total: 0 });

    const [deleted, setDeleted] = useState(null);

    const [searchText, setSearchText] = useState(null);

    //  Phân trang
    const [pagination, setPagination] = useState({
        current: 1,
        pageSize: 5,
        total: 0,
    });


    const fetchColors = async () => {
        try {
            const response = await ColorService.getAll(
                pagination.current - 1,
                pagination.pageSize,
                // searchName
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
                        const formattedProducers = responseData.map(colors => ({
                            key: colors.id,
                            id: colors.id,
                            code: colors.code,
                            colorName: colors.colorName,
                            ghi_chu: colors.ghi_chu,
                            dateCreate: new Date(colors.dateCreate).toLocaleString(),
                            dateUpdate: colors.dateUpdate ? new Date(colors.dateUpdate).toLocaleString() : 'N/A',
                            status: String(colors.status),  // Chuyển đổi thành chuỗi 
                        }));
                        setColors(formattedProducers);
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
        fetchColors();
    }, [pagination.current, pagination.pageSize, searchText, deleted]);


    const handleDelete = async (id) => {

        await ColorService.delete(id).then(() => {

            fetchColors();
        }).catch(error => {
            console.error(error);
            notification.error({
                message: 'Thông báo',
                description: 'Đã có lỗi xảy ra!',
            });
        });

    };

    const handleReset = () => {

        setSearchText(null);
        setDeleted(null);

        setPagination({
            ...pagination,
            current: 1,
        });
        // handleTableChange(pagination, null)
    };

    const handleTableChange = (pagination, filters) => {

        setPagination({
            ...pagination,
        });
        const statusFilter = filters?.deleted;
        const searchFilter = filters?.colorName;
        // Kiểm tra nếu statusFilter không tồn tại hoặc là mảng rỗng
        const isNoStatusFilter = !statusFilter || statusFilter.length === 0;

        if (searchFilter) {
            setSearchText(searchFilter[0]);
        } else {
            setSearchText(null)
        }
        // Kiểm tra nếu có lựa chọn bộ lọc và không phải là trường hợp không chọn
        if (!isNoStatusFilter) {
            const isBothStatus = statusFilter.length === 2;

            // Sử dụng biểu thức điều kiện để xác định trạng thái để lọc
            setDeleted(isBothStatus ? null : statusFilter[0]);
        } else {
            // Nếu không có lựa chọn bộ lọc, đặt trạng thái deleted về null hoặc giá trị mặc định
            setDeleted(null);
        }
    };
    const getColumnSearchProps = (dataIndex) => ({
        filteredValue: [searchText] || null,
        filterDropdown: ({ setSelectedKeys, selectedKeys, confirm }) => (
            <Input.Search
                placeholder={`Nhập tên...`}
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
            render: (value, item, index) => (pagination.current - 1) * pagination.pageSize + index + 1
        },

        {
            title: 'Mã',
            dataIndex: 'code',
            key: 'code',
            width: '15%',
        },
        {
            title: 'Tên nhà màu sắc',
            dataIndex: 'colorName',
            key: 'colorName',
            width: '15%',
            filterIcon: <SearchOutlined style={{ fontSize: '14px', color: 'rgb(158, 154, 154)' }} />,
            ...getColumnSearchProps('colorName')
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
            width: '15%',
        },

        {
            title: 'Ngày sửa',
            dataIndex: 'dateUpdate',
            key: 'dateUpdate',
            width: '15%',
        },
        // {
        //     title: 'Người tạo',
        //     dataIndex: 'createdBy',
        //     key: 'createdBy',
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
                    <Button type="text"
                        icon={<FormOutlined style={{ color: 'rgb(214, 103, 12)' }} />}
                        onClick={() => showModal("edit", record)} />
                    <Switch
                        size="small"
                        defaultChecked={record.deleted}
                        onClick={() => handleDelete(record.id)}
                    />
                </Space>
            }

        },
    ];

    return (
        <>
            <h3 style={{ marginBottom: '16px', float: 'left', color: '#2123bf' }}>Danh sách màu sắc</h3>

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
                dataSource={colors.map((color, index) => ({
                    ...color,
                    key: index + 1,
                    createdAt: FormatDate(color.createdAt)
                }))}
                onChange={handleTableChange}
                // loading={loading}
                columns={columns}
                pagination={{
                    current: pagination.current,
                    pageSize: pagination.pageSize,
                    defaultPageSize: 5,
                    pageSizeOptions: ['5', '10', '15'],
                    total: pagination.total,
                    showSizeChanger: true,
                    
                }}></Table > */}


            <Table
                dataSource={colors}
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

            {/* {open.isModal && <ColorModal
                isMode={open.isMode}
                reacord={open.reacord || {}}
                hideModal={hideModal}
                isModal={open.isModal}
                colors={colors}
                fetchColors={fetchColors} />} */}

            {open.isModal && <ColorModal
                isMode={open.isMode}
                reacord={open.reacord || {}}
                hideModal={hideModal}
                isModal={open.isModal}
                colors={colors}
                fetchColors={fetchColors} />}

        </>
    )
};
export default Color;


const ColorModal = ({ isMode, reacord, hideModal, isModal, fetchColors, colors }) => {

    const [form] = Form.useForm();

    const handleCreate = () => {
        form.validateFields().then(async () => {

            const data = form.getFieldsValue();

            await ColorService.create(data)
                .then(() => {
                    notification.success({
                        message: 'Thông báo',
                        description: 'Thêm mới thành công!',
                    });
                    fetchColors();
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
    const handleUpdate = () => {
        form.validateFields().then(async () => {

            const data = form.getFieldsValue();

            await ColorService.update(reacord.id, data)
                .then(() => {
                    notification.success({
                        message: 'Thông báo',
                        description: 'Cập nhật thành công!',
                    });
                    fetchColors();
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

    return (

        <Modal
            title={isMode === "edit" ? "Cập nhật màu sắc" : "Thêm mới một màu sắc"}
            open={isModal}
            onOk={isMode === "edit" ? handleUpdate : handleCreate}
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
                <Form.Item label="Tên:" name="colorName"
                    rules={[
                        { required: true, message: 'Vui lòng nhập tên màu sắc!' },
                        {
                            validator: (_, value) => {
                                if (!value) {
                                    return Promise.resolve(); // Không thực hiện validate khi giá trị rỗng
                                }
                                const trimmedValue = value.trim(); // Loại bỏ dấu cách ở đầu và cuối
                                const lowercaseValue = trimmedValue.toLowerCase(); // Chuyển về chữ thường
                                const isDuplicate = colors.some(
                                    (color) => color.colorName.trim().toLowerCase() === lowercaseValue && color.id !== reacord.id
                                );
                                if (isDuplicate) {
                                    return Promise.reject('Tên màu sắc đã tồn tại!');
                                }
                                // Kiểm tra dấu cách ở đầu và cuối
                                if (/^\s|\s$/.test(value)) {
                                    return Promise.reject('Tên màu sắc không được chứa dấu cách ở đầu và cuối!');
                                }
                                return Promise.resolve();
                            },
                        },
                    ]}>
                    <Input placeholder="Nhập tên màu sắc..." />
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

