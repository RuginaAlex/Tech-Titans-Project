--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2023-12-23 08:20:08

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
-- TOC entry 216 (class 1259 OID 24665)
-- Name: bank_accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bank_accounts (
    account_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    user_id bigint NOT NULL,
    account_number character varying(34) NOT NULL,
    currency character varying(3) NOT NULL,
    balance double precision NOT NULL,
    account_type character varying(256) NOT NULL
);


ALTER TABLE public.bank_accounts OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24692)
-- Name: budgets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.budgets (
    budget_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    user_id bigint NOT NULL,
    category_id bigint NOT NULL,
    amount double precision NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL
);


ALTER TABLE public.budgets OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24687)
-- Name: expense_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.expense_categories (
    category_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name character varying(256) NOT NULL
);


ALTER TABLE public.expense_categories OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24717)
-- Name: investment_recommendations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.investment_recommendations (
    recommendation_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    user_id bigint NOT NULL,
    description character varying(256) NOT NULL,
    risk_level character varying(256) NOT NULL,
    potential_return double precision NOT NULL
);


ALTER TABLE public.investment_recommendations OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24707)
-- Name: saving_goals; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.saving_goals (
    goal_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    user_id bigint NOT NULL,
    target_amount double precision NOT NULL,
    current_amount double precision NOT NULL,
    deadline date NOT NULL
);


ALTER TABLE public.saving_goals OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24675)
-- Name: transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions (
    transaction_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    account_id bigint NOT NULL,
    amount double precision NOT NULL,
    type character varying(256) NOT NULL,
    "timestamp" date NOT NULL,
    description character varying(256) NOT NULL
);


ALTER TABLE public.transactions OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 24658)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    username character varying(256) NOT NULL,
    email character varying(256) NOT NULL,
    password_hash character varying(256) NOT NULL,
    date_created date NOT NULL,
    last_login date NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 4875 (class 0 OID 24665)
-- Dependencies: 216
-- Data for Name: bank_accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bank_accounts (account_id, user_id, account_number, currency, balance, account_type) FROM stdin;
\.

--
-- TOC entry 4878 (class 0 OID 24692)
-- Dependencies: 219
-- Data for Name: budgets; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.budgets (budget_id, user_id, category_id, amount, start_date, end_date) FROM stdin;
\.

--
-- TOC entry 4877 (class 0 OID 24687)
-- Dependencies: 218
-- Data for Name: expense_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.expense_categories (category_id, name) FROM stdin;
\.

--
-- TOC entry 4880 (class 0 OID 24717)
-- Dependencies: 221
-- Data for Name: investment_recommendations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.investment_recommendations (recommendation_id, user_id, description, risk_level, potential_return) FROM stdin;
\.

--
-- TOC entry 4879 (class 0 OID 24707)
-- Dependencies: 220
-- Data for Name: saving_goals; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.saving_goals (goal_id, user_id, target_amount, current_amount, deadline) FROM stdin;
\.

--
-- TOC entry 4876 (class 0 OID 24675)
-- Dependencies: 217
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactions (transaction_id, account_id, amount, type, "timestamp", description) FROM stdin;
\.

--
-- TOC entry 4874 (class 0 OID 24658)
-- Dependencies: 215
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_id, username, email, password_hash, last_login) FROM stdin;
\.

--
-- Altering Primary Key Constraints for bigint compatibility
--

ALTER TABLE ONLY public.bank_accounts
    ADD CONSTRAINT bank_accounts_pkey PRIMARY KEY (account_id);

ALTER TABLE ONLY public.budgets
    ADD CONSTRAINT budgets_pkey PRIMARY KEY (budget_id);

ALTER TABLE ONLY public.expense_categories
    ADD CONSTRAINT expense_categories_pkey PRIMARY KEY (category_id);

ALTER TABLE ONLY public.investment_recommendations
    ADD CONSTRAINT investment_recommendations_pkey PRIMARY KEY (recommendation_id);

ALTER TABLE ONLY public.saving_goals
    ADD CONSTRAINT saving_goals_pkey PRIMARY KEY (goal_id);

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (transaction_id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);

--
-- Altering Foreign Key Constraints for bigint compatibility
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT account_id_fkey FOREIGN KEY (account_id) REFERENCES public.bank_accounts(account_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY public.budgets
    ADD CONSTRAINT category_id_fkey FOREIGN KEY (category_id) REFERENCES public.expense_categories(category_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY public.bank_accounts
    ADD CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY public.budgets
    ADD CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY public.saving_goals
    ADD CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY public.investment_recommendations
    ADD CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(user_id) ON UPDATE CASCADE ON DELETE CASCADE;

--
-- PostgreSQL database dump complete
--
