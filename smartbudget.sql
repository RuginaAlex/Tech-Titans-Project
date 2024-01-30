--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bank_accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bank_accounts (
    account_id integer NOT NULL,
    user_id integer NOT NULL,
    account_number character varying(34) NOT NULL,
    currency character varying(3) NOT NULL,
    balance double precision NOT NULL,
    account_type character varying(256) NOT NULL
);


ALTER TABLE public.bank_accounts OWNER TO postgres;

--
-- Name: bank_accounts_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.bank_accounts ALTER COLUMN account_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.bank_accounts_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: bugdets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bugdets (
    budget_id integer NOT NULL,
    amount double precision NOT NULL,
    end_date timestamp(6) without time zone NOT NULL,
    start_date timestamp(6) without time zone NOT NULL,
    category_id integer,
    user_id integer
);


ALTER TABLE public.bugdets OWNER TO postgres;

--
-- Name: companies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.companies (
    id_company integer NOT NULL,
    name character varying(256) NOT NULL,
    grade character varying(256) NOT NULL,
    ticker character varying(256) NOT NULL,
    last_date_fetched timestamp(6) without time zone
);


ALTER TABLE public.companies OWNER TO postgres;

--
-- Name: companies_id_company_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.companies ALTER COLUMN id_company ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.companies_id_company_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: company_data; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company_data (
    id_data bigint NOT NULL,
    id_company integer,
    open double precision NOT NULL,
    high double precision,
    low double precision,
    close double precision,
    date timestamp(6) without time zone
);


ALTER TABLE public.company_data OWNER TO postgres;

--
-- Name: company_data_id_data_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.company_data ALTER COLUMN id_data ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.company_data_id_data_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: expense_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.expense_categories (
    category_id integer NOT NULL,
    name character varying(256) NOT NULL
);


ALTER TABLE public.expense_categories OWNER TO postgres;

--
-- Name: expense_categories_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.expense_categories ALTER COLUMN category_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.expense_categories_category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: investment_recommendations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.investment_recommendations (
    recommendation_id integer NOT NULL,
    user_id integer NOT NULL,
    description character varying(256) NOT NULL,
    risk_level character varying(256) NOT NULL,
    potential_return double precision NOT NULL
);


ALTER TABLE public.investment_recommendations OWNER TO postgres;

--
-- Name: investment_recommendations_recommendation_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.investment_recommendations ALTER COLUMN recommendation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.investment_recommendations_recommendation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: saving_goals; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.saving_goals (
    goal_id integer NOT NULL,
    user_id integer NOT NULL,
    target_amount double precision NOT NULL,
    current_amount double precision NOT NULL,
    deadline timestamp(6) without time zone NOT NULL
);


ALTER TABLE public.saving_goals OWNER TO postgres;

--
-- Name: saving_goals_goal_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.saving_goals ALTER COLUMN goal_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.saving_goals_goal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions (
    transaction_id integer NOT NULL,
    account_id integer NOT NULL,
    amount double precision NOT NULL,
    type character varying(256) NOT NULL,
    "timestamp" timestamp(6) without time zone NOT NULL,
    description character varying(256) NOT NULL
);


ALTER TABLE public.transactions OWNER TO postgres;

--
-- Name: transactions_transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.transactions ALTER COLUMN transaction_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.transactions_transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    username character varying(256) NOT NULL,
    email character varying(256) NOT NULL,
    password_hash character varying(256) NOT NULL,
    date_created timestamp(6) without time zone NOT NULL,
    last_login timestamp(6) without time zone NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: bank_accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bank_accounts (account_id, user_id, account_number, currency, balance, account_type) FROM stdin;
\.


--
-- Data for Name: bugdets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bugdets (budget_id, amount, end_date, start_date, category_id, user_id) FROM stdin;
\.


--
-- Data for Name: companies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.companies (id_company, name, grade, ticker, last_date_fetched) FROM stdin;
1	Apple	Ungraded	AAPL	2021-01-03 00:00:00
\.


--
-- Data for Name: company_data; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.company_data (id_data, id_company, open, high, low, close, date) FROM stdin;
\.


--
-- Data for Name: expense_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.expense_categories (category_id, name) FROM stdin;
\.


--
-- Data for Name: investment_recommendations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.investment_recommendations (recommendation_id, user_id, description, risk_level, potential_return) FROM stdin;
\.


--
-- Data for Name: saving_goals; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.saving_goals (goal_id, user_id, target_amount, current_amount, deadline) FROM stdin;
\.


--
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions (transaction_id, account_id, amount, type, "timestamp", description) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, username, email, password_hash, date_created, last_login) FROM stdin;
1	dsgdfs	adsffd@sdf.com	Adjafsihdsugaj2@	2021-12-31 02:00:00	2022-12-31 02:00:00
\.


--
-- Name: bank_accounts_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bank_accounts_account_id_seq', 1, false);


--
-- Name: companies_id_company_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.companies_id_company_seq', 1, true);


--
-- Name: company_data_id_data_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.company_data_id_data_seq', 1, false);


--
-- Name: expense_categories_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.expense_categories_category_id_seq', 1, false);


--
-- Name: investment_recommendations_recommendation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.investment_recommendations_recommendation_id_seq', 1, false);


--
-- Name: saving_goals_goal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.saving_goals_goal_id_seq', 1, false);


--
-- Name: transactions_transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transactions_transaction_id_seq', 1, false);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_user_id_seq', 1, true);


--
-- Name: bank_accounts bank_accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank_accounts
    ADD CONSTRAINT bank_accounts_pkey PRIMARY KEY (account_id);


--
-- Name: bugdets bugdets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bugdets
    ADD CONSTRAINT bugdets_pkey PRIMARY KEY (budget_id);


--
-- Name: companies companies_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companies
    ADD CONSTRAINT companies_pk PRIMARY KEY (id_company);


--
-- Name: companies companies_pk_2; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.companies
    ADD CONSTRAINT companies_pk_2 UNIQUE (ticker);


--
-- Name: company_data company_data_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_data
    ADD CONSTRAINT company_data_pk PRIMARY KEY (id_data);


--
-- Name: expense_categories expense_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.expense_categories
    ADD CONSTRAINT expense_categories_pkey PRIMARY KEY (category_id);


--
-- Name: investment_recommendations investment_recommendations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.investment_recommendations
    ADD CONSTRAINT investment_recommendations_pkey PRIMARY KEY (recommendation_id);


--
-- Name: saving_goals saving_goals_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.saving_goals
    ADD CONSTRAINT saving_goals_pkey PRIMARY KEY (goal_id);


--
-- Name: transactions transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (transaction_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: transactions account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT account_id_fkey FOREIGN KEY (account_id) REFERENCES public.bank_accounts(account_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: company_data company_data_companies_id_company_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.company_data
    ADD CONSTRAINT company_data_companies_id_company_fk FOREIGN KEY (id_company) REFERENCES public.companies(id_company) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: bugdets fk1hmt8fkmgmb79s8lh2rn4fr57; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bugdets
    ADD CONSTRAINT fk1hmt8fkmgmb79s8lh2rn4fr57 FOREIGN KEY (category_id) REFERENCES public.expense_categories(category_id);


--
-- Name: bugdets fkjdmdgvxq9286khvoyibg61dwn; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bugdets
    ADD CONSTRAINT fkjdmdgvxq9286khvoyibg61dwn FOREIGN KEY (user_id) REFERENCES public.users(user_id);


--
-- Name: bank_accounts user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bank_accounts
    ADD CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: investment_recommendations user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.investment_recommendations
    ADD CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: saving_goals user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.saving_goals
    ADD CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

