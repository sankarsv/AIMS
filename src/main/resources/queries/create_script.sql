-- Table: aims.hc_intermediate

-- DROP TABLE aims.hc_intermediate;

CREATE TABLE aims.hc_intermediate
(
    upload_time date NOT NULL,
    filedata bytea NOT NULL,
    file_id bigint,
    CONSTRAINT hc_intermediate_pk PRIMARY KEY (upload_time)
)

TABLESPACE pg_default;

ALTER TABLE aims.hc_intermediate
    OWNER to postgres;

-- Table: aims.br_intermediate

-- DROP TABLE aims.br_intermediate;

CREATE TABLE aims.br_intermediate
(
    file_id bigint NOT NULL,
    filedata bytea NOT NULL,
    upload_time timestamp without time zone NOT NULL,
    CONSTRAINT br_intermediate_pkey PRIMARY KEY (file_id),
    CONSTRAINT uk_aahwdwf337y6x7s4xyw7nn7x5 UNIQUE (upload_time)
)

TABLESPACE pg_default;

ALTER TABLE aims.br_intermediate
    OWNER to postgres;

-- Table: aims.batch_audit

-- DROP TABLE aims.batch_audit;

CREATE TABLE aims.batch_audit
(
    file_id bigint NOT NULL,
    batch_status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    changed_date timestamp without time zone,
    file_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    added_date timestamp without time zone NOT NULL,
    CONSTRAINT batch_audit_pkey PRIMARY KEY (file_id)
)

TABLESPACE pg_default;

ALTER TABLE aims.batch_audit
    OWNER to postgres;

 -- Table: aims.allocation

-- DROP TABLE aims.allocation;

CREATE TABLE aims.allocation
(
    id integer NOT NULL DEFAULT nextval('allocation_id_seq'::regclass),
    depute_branch character varying(255) COLLATE pg_catalog."default" NOT NULL,
    depute_dc character varying(255) COLLATE pg_catalog."default" NOT NULL,
    employee_active_client_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    employee_location_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    employee_travel_country character varying(255) COLLATE pg_catalog."default" NOT NULL,
    end_date date NOT NULL,
    project_change_date date NOT NULL,
    start_date date NOT NULL,
    team_role character varying(255) COLLATE pg_catalog."default" NOT NULL,
    travel_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    employee_id integer,
    portfolio_id integer,
    project_id integer,
    won_id integer,
    CONSTRAINT allocation_pkey PRIMARY KEY (id),
    CONSTRAINT fk2ad4i8bkfr3p4ga510f07hecn FOREIGN KEY (won_id)
        REFERENCES aims.won_portfolio_mapping (won_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkchog19mdlip4ygulq94xwp9fg FOREIGN KEY (portfolio_id)
        REFERENCES aims.portfolio (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkf8m03xr64gx14k0sg4070811 FOREIGN KEY (project_id)
        REFERENCES aims.project (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkiqr8ngnig24356m97152phoxq FOREIGN KEY (employee_id)
        REFERENCES aims.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE aims.allocation
    OWNER to postgres;


-- Table: aims.employee

-- DROP TABLE aims.employee;

CREATE TABLE aims.employee
(
    id integer NOT NULL DEFAULT nextval('employee_id_seq'::regclass),
    aims_exp character varying(255) COLLATE pg_catalog."default",
    base_branch character varying(255) COLLATE pg_catalog."default",
    base_country character varying(255) COLLATE pg_catalog."default",
    base_dc character varying(255) COLLATE pg_catalog."default",
    category_name character varying(255) COLLATE pg_catalog."default",
    created_at timestamp without time zone,
    current_location character varying(255) COLLATE pg_catalog."default",
    dob character varying(255) COLLATE pg_catalog."default",
    employee_type character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default",
    gender character varying(255) COLLATE pg_catalog."default",
    grade character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    overall_exp character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT employee_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE aims.employee
    OWNER to postgres;


-- Table: aims.employee_allocation_percentage

-- DROP TABLE aims.employee_allocation_percentage;

CREATE TABLE aims.employee_allocation_percentage
(
    id integer NOT NULL DEFAULT nextval('employee_allocation_percentage_id_seq'::regclass),
    percentage_allocation integer NOT NULL,
    employee_id integer,
    CONSTRAINT employee_allocation_percentage_pkey PRIMARY KEY (id),
    CONSTRAINT fkp00uole7moe8y8w0k7h5lc1xu FOREIGN KEY (employee_id)
        REFERENCES aims.allocation (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE aims.employee_allocation_percentage
    OWNER to postgres;


-- Table: aims.hcmaster

-- DROP TABLE aims.hcmaster;

CREATE TABLE aims.hcmaster
(
    version_no integer NOT NULL,
    employee_id integer NOT NULL,
    allocation_end character varying(255) COLLATE pg_catalog."default",
    allocation_start character varying(255) COLLATE pg_catalog."default",
    am character varying(255) COLLATE pg_catalog."default",
    am_id character varying(255) COLLATE pg_catalog."default",
    base_branch character varying(255) COLLATE pg_catalog."default",
    base_country character varying(255) COLLATE pg_catalog."default",
    base_dc character varying(255) COLLATE pg_catalog."default",
    brm character varying(255) COLLATE pg_catalog."default",
    brm_1 character varying(255) COLLATE pg_catalog."default",
    cc_ind character varying(255) COLLATE pg_catalog."default",
    child_iou character varying(255) COLLATE pg_catalog."default",
    client_country character varying(255) COLLATE pg_catalog."default",
    client_geography character varying(255) COLLATE pg_catalog."default",
    cluster character varying(255) COLLATE pg_catalog."default",
    customer character varying(255) COLLATE pg_catalog."default",
    dc character varying(255) COLLATE pg_catalog."default",
    depute_dc character varying(255) COLLATE pg_catalog."default",
    designation character varying(255) COLLATE pg_catalog."default",
    deputeb_ranch character varying(255) COLLATE pg_catalog."default",
    dm character varying(255) COLLATE pg_catalog."default",
    doj character varying(255) COLLATE pg_catalog."default",
    employee_location character varying(255) COLLATE pg_catalog."default",
    emp_name character varying(255) COLLATE pg_catalog."default",
    employee_type character varying(255) COLLATE pg_catalog."default",
    employee_cluster character varying(255) COLLATE pg_catalog."default",
    experience character varying(255) COLLATE pg_catalog."default",
    gl character varying(255) COLLATE pg_catalog."default",
    grade character varying(255) COLLATE pg_catalog."default",
    group_customer character varying(255) COLLATE pg_catalog."default",
    iou character varying(255) COLLATE pg_catalog."default",
    ip character varying(255) COLLATE pg_catalog."default",
    mapp_designation character varying(255) COLLATE pg_catalog."default",
    parent_iou character varying(255) COLLATE pg_catalog."default",
    percentage_allocation character varying(255) COLLATE pg_catalog."default",
    person_type character varying(255) COLLATE pg_catalog."default",
    pl character varying(255) COLLATE pg_catalog."default",
    platform character varying(255) COLLATE pg_catalog."default",
    project_id character varying(255) COLLATE pg_catalog."default",
    project_location character varying(255) COLLATE pg_catalog."default",
    project_name character varying(255) COLLATE pg_catalog."default",
    project_type character varying(255) COLLATE pg_catalog."default",
    rm character varying(255) COLLATE pg_catalog."default",
    sdb_name character varying(255) COLLATE pg_catalog."default",
    senior_junior character varying(255) COLLATE pg_catalog."default",
    site character varying(255) COLLATE pg_catalog."default",
    swoncategory character varying(255) COLLATE pg_catalog."default",
    sp character varying(255) COLLATE pg_catalog."default",
    sub_person_type character varying(255) COLLATE pg_catalog."default",
    sub_sp character varying(255) COLLATE pg_catalog."default",
    sub_iou character varying(255) COLLATE pg_catalog."default",
    team_role character varying(255) COLLATE pg_catalog."default",
    total_experience character varying(255) COLLATE pg_catalog."default",
    travel_country character varying(255) COLLATE pg_catalog."default",
    travel_type character varying(255) COLLATE pg_catalog."default",
    turnkey character varying(255) COLLATE pg_catalog."default",
    work_country character varying(255) COLLATE pg_catalog."default",
    work_geography character varying(255) COLLATE pg_catalog."default",
    work_location character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT hcmaster_pkey PRIMARY KEY (version_no, employee_id)
)

TABLESPACE pg_default;

ALTER TABLE aims.hcmaster
    OWNER to postgres;

-- Table: aims.hcversion

-- DROP TABLE aims.hcversion;

CREATE TABLE aims.hcversion
(
    version_no integer NOT NULL,
    load_date timestamp without time zone,
    CONSTRAINT hcversion_pkey PRIMARY KEY (version_no)
)

TABLESPACE pg_default;

ALTER TABLE aims.hcversion
    OWNER to postgres;


-- Table: aims.portfolio

-- DROP TABLE aims.portfolio;

CREATE TABLE aims.portfolio
(
    id integer NOT NULL DEFAULT nextval('portfolio_id_seq'::regclass),
    billing_emp_id integer NOT NULL,
    brm_empid integer NOT NULL,
    description integer NOT NULL,
    dm_emp_id integer NOT NULL,
    offshore_lead_emp_id integer NOT NULL,
    onsite_lead_empid integer NOT NULL,
    portfolio_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    portfolio_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT portfolio_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE aims.portfolio
    OWNER to postgres;



-- Table: aims.project

-- DROP TABLE aims.project;

CREATE TABLE aims.project
(
    id integer NOT NULL DEFAULT nextval('project_id_seq'::regclass),
    bfs_cluster character varying(255) COLLATE pg_catalog."default" NOT NULL,
    child_iou_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    client_country character varying(255) COLLATE pg_catalog."default" NOT NULL,
    client_geography character varying(255) COLLATE pg_catalog."default" NOT NULL,
    customer character varying(255) COLLATE pg_catalog."default" NOT NULL,
    group_customer character varying(255) COLLATE pg_catalog."default" NOT NULL,
    iou character varying(255) COLLATE pg_catalog."default" NOT NULL,
    ip character varying(255) COLLATE pg_catalog."default" NOT NULL,
    parent_iou_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    project_change_date date NOT NULL,
    project_cluster character varying(255) COLLATE pg_catalog."default" NOT NULL,
    project_hash character varying(255) COLLATE pg_catalog."default" NOT NULL,
    project_location character varying(255) COLLATE pg_catalog."default" NOT NULL,
    project_location_wrt_india character varying(255) COLLATE pg_catalog."default" NOT NULL,
    project_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    project_type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    pure_turnkey_flag character varying(255) COLLATE pg_catalog."default" NOT NULL,
    sub_iou character varying(255) COLLATE pg_catalog."default" NOT NULL,
    swon_category character varying(255) COLLATE pg_catalog."default" NOT NULL,
    work_country character varying(255) COLLATE pg_catalog."default" NOT NULL,
    work_geography character varying(255) COLLATE pg_catalog."default" NOT NULL,
    work_location character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT project_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE aims.project
    OWNER to postgres;


-- Table: aims.role

-- DROP TABLE aims.role;

CREATE TABLE aims.role
(
    roleid integer NOT NULL,
    changed_date timestamp without time zone,
    rolename character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (roleid)
)

TABLESPACE pg_default;

ALTER TABLE aims.role
    OWNER to postgres;

-- Table: aims."user"

-- DROP TABLE aims."user";

CREATE TABLE aims."user"
(
    userid integer NOT NULL,
    changed_date timestamp without time zone,
    password character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (userid)
)

TABLESPACE pg_default;

ALTER TABLE aims."user"
    OWNER to postgres;

-- Table: aims.userrole

-- DROP TABLE aims.userrole;

CREATE TABLE aims.userrole
(
    changed_date timestamp without time zone,
    userid integer NOT NULL,
    roleid integer NOT NULL,
    CONSTRAINT userrole_pkey PRIMARY KEY (userid, roleid),
    CONSTRAINT fk67htjl9etm31euhn48np44ah7 FOREIGN KEY (userid)
        REFERENCES aims."user" (userid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkebf6c24ad1b71apmiowguuknc FOREIGN KEY (roleid)
        REFERENCES aims.role (roleid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE aims.userrole
    OWNER to postgres;
